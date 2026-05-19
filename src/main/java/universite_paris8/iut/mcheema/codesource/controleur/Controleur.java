package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    private Timeline gameLoop;
    private int temps;
    private Circle leCercle = new Circle(5, Color.RED);

    private Environnement environnement;

    @FXML
    private TilePane tilePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.environnement = new Environnement(1,640,480);
        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur()* Terrain.PIXEL_TUILLE,this.environnement.getTerrainDeJeu().obtenirHauteur()*Terrain.PIXEL_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(),this.tilePane);
        terrainVue.afficheTerrainJeu();
        this.initAnimation();
        this.gameLoop.play();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==100){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%5==0){
                        System.out.println("un tour");
                        leCercle.setLayoutX(leCercle.getLayoutX()+5);
                        leCercle.setLayoutY(leCercle.getLayoutY()+5);

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}