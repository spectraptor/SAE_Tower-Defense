package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.*;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ping;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.ErreurExecution;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.ChevalDeTroie;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.vue.BatimentVue;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/*

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Base base = new Base();
        this.labelVieBase.textProperty().bind(base.pvProperty().asString());
        this.environnement = new Environnement(1,base);

        // Listener sur l'Observable Liste d'ennemis
        this.environnement.getEnnemis().addListener(new ObservateurListeEnnemis(this.paneJeu));

        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur() * Terrain.TAILLE_TUILLE, this.environnement.getTerrainDeJeu().obtenirHauteur() * Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(), this.tilePane);

        // Définir l'entrée donc le point de spawn des ennemis et ou se situe la base à atteindre pour les ennemis
        Tuile baseTuile = new Tuile(12, 0);
        Tuile entree = new Tuile(4, 19);
        BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), entree);
        ArrayList<Tuile> chemin = bfs.cheminVersSource(baseTuile);
        Ennemi bug = new Bogue(0, 0, this.environnement);
        bug.setChemin(chemin);

        for(int i = 0;i<10;i++) {
            Ennemi ennemi = new Ping(this.environnement);
            if(i==1) {
                ennemi = new Bogue(this.environnement);
            }
            else if (i<5) {
                ennemi = new ChevalDeTroie(this.environnement);
            }
            else {
                ennemi = new ErreurExecution(this.environnement);
            }
            ennemi.setChemin(chemin);
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
                    !this.environnement.estAdjacentATour(centreTuileX, centreTuileY)) {

                Batiment batiment = new Tour1(centreTuileX, centreTuileY, this.environnement);
                this.environnement.ajouterBatiment(batiment);

                System.out.println(batiment);

                BatimentVue batimentVue = new BatimentVue(batiment, this.paneJeu);
                batimentVue.creerSpriteBatiment();

                System.out.println("Pos. souris : " + coordsSourisX + ";" + coordsSourisY);

            }
        }
    }

}