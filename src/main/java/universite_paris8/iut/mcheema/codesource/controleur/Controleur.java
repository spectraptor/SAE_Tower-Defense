package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.batiment.*;
import universite_paris8.iut.mcheema.codesource.controleur.listeners.ObservateurListeEnnemis;
import universite_paris8.iut.mcheema.codesource.controleur.listeners.ObservateurListeProjectiles;
import universite_paris8.iut.mcheema.codesource.modele.*;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.ErreurExecution;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.vue.BatimentVue;
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
    private  Button pauseReprendre;

    @FXML
    private Button pauseQuitter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        this.environnement = new Environnement(2);
        this.labelVieBase.textProperty().bind(this.environnement.getBase().pvProperty().asString());
        this.labelArgent.textProperty().bind(this.environnement.argentProperty().asString());

        // Listener sur l'Observable Liste d'ennemis
        this.environnement.getEnnemis().addListener(new ObservateurListeEnnemis(this.paneJeu));
        this.environnement.getProjectiles().addListener(new ObservateurListeProjectiles(this.paneJeu));

        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur() * Terrain.TAILLE_TUILLE, this.environnement.getTerrainDeJeu().obtenirHauteur() * Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(), this.tilePane);

        // Définir l'entrée donc le point de spawn des ennemis et ou se situe la base à atteindre pour les ennemis
        /*
        Tuile baseTuile = new Tuile(0, 12);
        Tuile entree = new Tuile(19, 4);
        BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), entree);
        ArrayList<Tuile> chemin = bfs.cheminVersSource(baseTuile);

         */

        // Carte 2
        Point basePoint = new Point(0, 7);
        Point entree = new Point(15, 0);
        BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), basePoint);
        ArrayList<Point> chemin = bfs.cheminDepuisSource(entree);

        Point basePoint2 = new Point(0, 11);
        Point entree2 = new Point(13, 14);
        BFS bfs2 = new BFS(this.environnement.getTerrainDeJeu(), basePoint2);
        ArrayList<Point> chemin2 = bfs2.cheminDepuisSource(entree2);


        Ennemi ennemi;
        for(int i = 0;i<2;i++) {
            if(i==0) {
                ennemi = new Bogue(this.environnement, chemin);
            }
            else {
                ennemi = new ErreurExecution(this.environnement, chemin2);
            }

            this.environnement.ajouterEnnemi(ennemi);
        }

        terrainVue.afficheTerrainJeu();
        this.initAnimation();

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
                if (!this.environnement.tuileEstAccessibleCoords(coordsSourisX, coordsSourisY) &&
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

                    this.environnement.ajouterBatiment(batiment);

                    BatimentVue surcadenceVue = new BatimentVue(batiment, paneJeu);
                    surcadenceVue.creerSpriteBatiment();

                    System.out.println("Pos. souris : " + coordsSourisX + ";" + coordsSourisY);
                }
            }
        }


            System.out.println("Colonne : " + mouseEvent.getX() / 32 + " ligne : " + mouseEvent.getY() / 32);
        }
    }

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

    public void afficheReglage(ActionEvent actionEvent) {
        etatPause = true;
        gameLoop.pause();
        menuPause.setVisible(true);
    }



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

    public void lancer(ActionEvent actionEvent) {
        if (this.etatPause) {
            etatPause = false;
            this.gameLoop.play();
            menuPause.setVisible(false);
            System.out.println("Jeu mis en lancer");
        } else {
            this.gameLoop.pause();
            this.etatPause = true;
            System.out.println("Jeu mis en pause");
        }
    }


    public void quitter(ActionEvent actionEvent) {
        System.out.println("fait rien pour l'instant");
    }
}
