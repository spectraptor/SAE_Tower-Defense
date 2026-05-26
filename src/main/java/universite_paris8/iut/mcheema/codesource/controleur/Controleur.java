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
import universite_paris8.iut.mcheema.codesource.vue.BaseVue;
import universite_paris8.iut.mcheema.codesource.vue.EnnemiVue;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

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
        Base base = new Base(630,145);
        BaseVue baseVue = new BaseVue(this.paneJeu,base);
        baseVue.creeSpriteBase();
        this.labelVieBase.textProperty().bind(base.pvProperty().asString());
        this.environnement = new Environnement(1,base);

        this.environnement.getEnnemis().addListener(new ObservateurListeEnnemis(this));

        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur()* Terrain.TAILLE_TUILLE,this.environnement.getTerrainDeJeu().obtenirHauteur()*Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(),this.tilePane);

        for(int i = 0 ;i< 50;i++) {
            Ennemi bug = new Bogue(368 , 199, this.environnement); // (560, 140) si on veut essayer qu'il atteigne la fin
            this.environnement.ajouterEnnemi(bug);

            EnnemiVue bugVue =  new EnnemiVue(bug, paneJeu);
            bugVue.creerSpriteEnnemi();
        }


        terrainVue.afficheTerrainJeu();

        this.initAnimation();
        this.gameLoop.play();

    }

    private void initAnimation() {
        for(Ennemi e: this.environnement.getEnnemis()) {
            e.attribuerDirectionAleatoire();
        }

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                ev -> {
                    this.environnement.unTour();

                    if (this.environnement.getBase().estDetruite()) {
                        System.out.println("Vous avez perdu");
                        gameLoop.stop();
                    }
                    if(this.environnement.getEnnemis().isEmpty()) {
                        System.out.println("Vous avez gagné");
                        gameLoop.stop();
                    }
                }
        );

        gameLoop.getKeyFrames().add(kf);
    }

    public Pane getPaneJeu() {
        return this.paneJeu;
    }

    public void test(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getX()+";"+mouseEvent.getY());
    }
}