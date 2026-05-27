package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.*;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.vue.EnnemiVue;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;
import java.net.URL;
import java.util.ArrayList;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.environnement = new Environnement(1);
        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur()* Terrain.TAILLE_TUILLE,this.environnement.getTerrainDeJeu().obtenirHauteur()*Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(),this.tilePane);

        Sommet base = new Sommet(12, 0);
        Sommet entree = new Sommet(4, 19);
        BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), entree);
        ArrayList<Sommet> chemin = bfs.cheminVersSource(base);
        Ennemi bug = new Bogue(0, 0, this.environnement);
        bug.setChemin(chemin);

        for (Sommet s : chemin) {
            System.out.println(
                    "Ligne: " + s.getLigne() + " Colonne: " + s.getColonne()
            );
        }

        this.environnement.ajouterEnnemi(bug);

        EnnemiVue bugVue =  new EnnemiVue(bug, paneJeu);
        bugVue.creerSpriteEnnemi();

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

}