package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import universite_paris8.iut.mcheema.codesource.controleur.listeners.ObservateurListeBatiments;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.batiment.*;
import universite_paris8.iut.mcheema.codesource.controleur.listeners.ObservateurListeEnnemis;
import universite_paris8.iut.mcheema.codesource.controleur.listeners.ObservateurListeProjectiles;
import universite_paris8.iut.mcheema.codesource.modele.*;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.ErreurExecution;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Le controleur fait la gestion entre le modèle et la vue.
 * Elle contient les listners, la gameloop et agit aussi selon les actions du joueur sur le jeu.
 */

public class Controleur implements Initializable {

    private Timeline gameLoop;

    private Environnement environnement;

    private boolean etatRapide = false;

    private boolean etatPause = true;

    private int numBatimentSelectionne = -1;

    @FXML
    private TilePane tilePane;

    @FXML
    private Pane paneJeu;

    @FXML
    private Label labelVieBase;

    @FXML
    private  Label labelArgent;

    @FXML
    private Pane menuPause;

    @FXML
    private Button boutonLancerPause;

    @FXML
    private BorderPane borderPanePrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image imgLancer = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/images/boutons/lancer.png").toExternalForm());
        ImageView imgVLancer = new ImageView(imgLancer);
        imgVLancer.setFitHeight(50);
        imgVLancer.setFitWidth(45);
        this.boutonLancerPause.setGraphic(imgVLancer);
        initAnimation();
    }

    public void chargerNiveau(int niveau) {

        this.environnement = new Environnement(niveau);
        Niveau niveauChoisi = new Niveau(niveau);
        this.labelVieBase.textProperty().bind(this.environnement.getBase().pvProperty().asString());
        this.labelArgent.textProperty().bind(this.environnement.argentProperty().asString());

        // Listener sur l'Observable Liste d'ennemis
        this.environnement.getEnnemis().addListener(new ObservateurListeEnnemis(this.paneJeu));
        this.environnement.getBatiments().addListener(new ObservateurListeBatiments(this.paneJeu));
        this.environnement.getProjectiles().addListener(new ObservateurListeProjectiles(this.paneJeu));

        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur() * Terrain.TAILLE_TUILLE, this.environnement.getTerrainDeJeu().obtenirHauteur() * Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(), this.tilePane);

        terrainVue.afficheTerrainJeu();

        for (int i = 0; i < niveauChoisi.getBases().size(); i++) {

            Point base = niveauChoisi.getBases().get(i);
            Point entree = niveauChoisi.getEntrees().get(i);
            BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), base);
            ArrayList<Point> chemin = bfs.cheminDepuisSource(entree);

            Ennemi ennemi;
            if (i == 0) {
                ennemi = new Bogue(this.environnement, chemin);
            }
            else {
                ennemi = new ErreurExecution(this.environnement, chemin);
            }

            this.environnement.ajouterEnnemi(ennemi);
        }
    }

    private void initAnimation() {

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                ev -> {
                    environnement.unTour();
                }
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    public void ajouterTour(MouseEvent mouseEvent) {
        if (numBatimentSelectionne != -1) {
        Batiment batiment = null;
        if (!this.environnement.partieEstFinie() && !etatPause) {
            int coordsSourisX = (int) mouseEvent.getX();
            int coordsSourisY = (int) mouseEvent.getY();

            int[] lignesColonnesTuile = this.environnement.getTerrainDeJeu().convertirCoordsTuile(coordsSourisX, coordsSourisY);

            int centreTuileX = lignesColonnesTuile[1] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
            int centreTuileY = lignesColonnesTuile[0] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
            if (!this.environnement.partieEstFinie()) {
                if (this.environnement.tuileTourPosable(coordsSourisX,coordsSourisY) &&
                        !this.environnement.tuileContientUnBatiment(centreTuileX, centreTuileY)) {

                    switch (this.numBatimentSelectionne) {
                        case 1:
                            batiment = new Compilateur(centreTuileX, centreTuileY, this.environnement);
                            break;
                        case 2:
                            batiment = new Debugger(centreTuileX, centreTuileY, this.environnement);
                            break;
                        case 3:
                            batiment = new BombeLogique(centreTuileX, centreTuileY, this.environnement);
                            break;
                        case 4:
                            batiment = new Surcadence(centreTuileX, centreTuileY, this.environnement);
                            break;
                    }

                    // réinitialisation du bouton
                    this.numBatimentSelectionne = -1;

                    this.environnement.ajouterBatiment(batiment);
                    System.out.println("Pos. souris : " + coordsSourisX + ";" + coordsSourisY);
                }
            }
        }


            System.out.println("Colonne : " + mouseEvent.getX() / 32 + " ligne : " + mouseEvent.getY() / 32);
        }
    }

    @FXML
    public void choisirBouton(ActionEvent actionEvent) {
        Button bouton = (Button) actionEvent.getSource();

        switch(bouton.getText()) {
            case "Compilateur", "Cloud", "RAM":
                this.numBatimentSelectionne = 1;
                break;
            case "Debugger":
                this.numBatimentSelectionne = 2;
                break;
            case "Bombe Logique":
                this.numBatimentSelectionne = 3;
                break;
            case "Surcadence":
                this.numBatimentSelectionne = 4;
                break;

        }
    }

    @FXML
    public void afficheReglage(ActionEvent actionEvent) {
        etatPause = true;
        gameLoop.pause();
        menuPause.setVisible(true);
    }

    @FXML
    public void accelererOuRalentir(ActionEvent actionEvent) {
        if (etatRapide) {
            gameLoop.setRate(1);
            etatRapide = false;
            System.out.println("Jeu mis en x1");

        }
        else {
            gameLoop.setRate(2);
            etatRapide = true;
            System.out.println("Jeu mis en x2");
        }
    }

    @FXML
    public void lancer(ActionEvent actionEvent) {
        if (this.etatPause) {
            etatPause = false;
            this.gameLoop.play();
            menuPause.setVisible(false);
            Image imgPause = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/images/boutons/pause.png").toExternalForm());
            ImageView imgVPause = new ImageView(imgPause);
            imgVPause.setFitHeight(50);
            imgVPause.setFitWidth(45);
            this.boutonLancerPause.setGraphic(imgVPause);
            System.out.println("Jeu mis en lancer");
        } else {
            this.gameLoop.pause();
            this.etatPause = true;
            Image imgLancer = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/images/boutons/lancer.png").toExternalForm());
            ImageView imgVLancer = new ImageView(imgLancer);
            imgVLancer.setFitHeight(50);
            imgVLancer.setFitWidth(45);
            this.boutonLancerPause.setGraphic(imgVLancer);
            System.out.println("Jeu mis en pause");
        }
    }

    @FXML
    public void quitter(ActionEvent actionEvent) {
        Stage stage = (Stage) this.borderPanePrincipal.getScene().getWindow();
        stage.close();
    }
}
