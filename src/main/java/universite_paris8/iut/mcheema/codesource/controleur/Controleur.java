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
import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.vue.BaseVue;
import universite_paris8.iut.mcheema.codesource.vue.BatimentVue;

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

    @FXML
    private Label labelVieBase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Base base = new Base(12,400);
        BaseVue baseVue = new BaseVue(this.paneJeu,base);
        baseVue.creeSpriteBase();
        this.labelVieBase.textProperty().bind(base.pvProperty().asString());
        this.environnement = new Environnement(1,base);

        this.environnement.getEnnemis().addListener(new ObservateurListeEnnemis(this));
        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur() * Terrain.TAILLE_TUILLE, this.environnement.getTerrainDeJeu().obtenirHauteur() * Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(), this.tilePane);

        Sommet base2 = new Sommet(12, 0);
        Sommet entree = new Sommet(4, 19);
        BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), entree);
        ArrayList<Sommet> chemin = bfs.cheminVersSource(base2);
        Ennemi bug = new Bogue(0, 0, this.environnement);
        bug.setChemin(chemin);

        for(int i = 0;i<251;i++) {
            Ennemi ennemi = new Ping(372, 209, this.environnement); // (560, 140) si on veut essayer qu'il atteigne la fin
            if(i==1) {
                ennemi = new Bogue(372, 209, this.environnement); // (560, 140) si on veut essayer qu'il atteigne la fin
            }
            else if (i<100) {
                ennemi = new ChevalDeTroie(372,209,this.environnement);
            }
            else if (i<250) {
                ennemi = new ErreurExecution(372,209,this.environnement);
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
                    /*
                    if(this.environnement.getEnnemis().isEmpty()) {
                        this.gameLoop.stop();
                        System.out.println("Vous avez gagné");
                    }
                    if(this.environnement.getBase().estDetruite()) {
                        this.gameLoop.stop();
                        System.out.println("Vous avez perdu");
                    }

                     */
                }
        );

        gameLoop.getKeyFrames().add(kf);
    }

    public Pane getPaneJeu() {
        return this.paneJeu;
    }


    @FXML
    public void ajouterTour(MouseEvent mouseEvent) {
        if (!this.environnement.partieEstFinie()) {
            int coordsSourisX = (int) mouseEvent.getX();
            int coordsSourisY = (int) mouseEvent.getY();
            // On ne peut pas placer des bâtiments sur le chemin -> obstrue l'ennemi.
            if (!this.environnement.tuileEstAccessibleCoords(coordsSourisX, coordsSourisY) &&
                    !this.environnement.estAdjacentATour(coordsSourisX, coordsSourisY)) {

                Batiment batiment = new Tour1(coordsSourisX, coordsSourisY, this.environnement);
                this.environnement.ajouterBatiment(batiment);

                System.out.println(batiment);

                BatimentVue batimentVue = new BatimentVue(batiment, this.paneJeu);
                batimentVue.creerSpriteBatiment();

            }
            System.out.println("Pos. souris : " + coordsSourisX + ";" + coordsSourisY);
        }
    }

}