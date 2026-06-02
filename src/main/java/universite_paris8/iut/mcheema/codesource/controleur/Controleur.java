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

    @FXML
    private TilePane tilePane;

    @FXML
    private Pane paneJeu;

    @FXML
    private Label labelVieBase;

    @FXML
    private Button tour1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.environnement = new Environnement(2);
        this.labelVieBase.textProperty().bind(this.environnement.getBase().pvProperty().asString());

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
        BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), entree);
        ArrayList<Point> chemin = bfs.cheminDepuisSource(basePoint);

        Point basePoint2 = new Point(0, 11);
        Point entree2 = new Point(13, 14);
        BFS bfs2 = new BFS(this.environnement.getTerrainDeJeu(), entree2);
        ArrayList<Point> chemin2 = bfs2.cheminDepuisSource(basePoint2);


        Ennemi ennemi;
        for(int i = 0;i<2;i++) {
            if(i==0) {
                ennemi = new Bogue(this.environnement);
                ennemi.setChemin(chemin);
            }
            else {
                ennemi = new ErreurExecution(this.environnement);
                ennemi.setChemin(chemin2);
            }

            this.environnement.ajouterEnnemi(ennemi);
        }

        terrainVue.afficheTerrainJeu();
        this.initAnimation();
        this.gameLoop.play();
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
        if (!this.environnement.partieEstFinie()) {
            int coordsSourisX = (int) mouseEvent.getX();
            int coordsSourisY = (int) mouseEvent.getY();

            int[] lignesColonnesTuile = this.environnement.getTerrainDeJeu().convertirCoordsTuile(coordsSourisX, coordsSourisY);

            int centreTuileX = lignesColonnesTuile[1] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
            int centreTuileY = lignesColonnesTuile[0] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;

            if (!this.environnement.tuileEstAccessibleCoords(coordsSourisX, coordsSourisY) &&
                    !this.environnement.tuileContientUnBatiment(centreTuileX, centreTuileY)) {

                Batiment batiment = new Tour1(centreTuileX, centreTuileY, this.environnement);
                this.environnement.ajouterBatiment(batiment);

                System.out.println(batiment);

                BatimentVue batimentVue = new BatimentVue(batiment, this.paneJeu);
                batimentVue.creerSpriteBatiment();

                System.out.println("Pos. souris : " + coordsSourisX + ";" + coordsSourisY);

            }
        }
    }

    public void choisirBouton(ActionEvent actionEvent) {
        Button bouton = (Button) actionEvent.getSource();

        switch(bouton.getText()) {
            case "Compilateur":
                System.out.println("choisir tour 1");
                break;
            case "Cloud":
                System.out.println("choisir tour 2");
                break;
            case "Debugger":
                System.out.println("choisir tour 3");
                break;
            case "Bombe Logique":
                System.out.println("choisir tour 4");
                break;
            case "Surcadence":
                System.out.println("choisir tour 5");
                break;
            case "RAM":
                System.out.println("choisir tour 6");
                break;

        }
    }
}
