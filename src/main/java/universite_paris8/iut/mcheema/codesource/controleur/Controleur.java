package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.Ennemi;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
  
    private Timeline gameLoop;
    private int temps;

    private Environnement environnement;

    @FXML
    private TilePane tilePane;


    @FXML
    private Pane paneJeu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.environnement = new Environnement(1,640,480);
        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur()* Terrain.TAILLE_TUILLE,this.environnement.getTerrainDeJeu().obtenirHauteur()*Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(),this.tilePane);
        Ennemi bug = new Bogue(400, 100, this.environnement);
        this.environnement.ajouterEnnemi(bug);
        creerSpriteEnnemi(bug);
        terrainVue.afficheTerrainJeu();
        //this.initAnimation();
        //this.gameLoop.play();

    }

    public void deplacerEnnemi(KeyEvent keyEvent) {
       switch (keyEvent.getCode()) {
           case Z:
               this.environnement.getEnnemis().get(0).setDx(0);
               this.environnement.getEnnemis().get(0).setDy(-1);
               this.environnement.getEnnemis().get(0).seDeplace();
               break;
           case S:
               this.environnement.getEnnemis().get(0).setDx(0);
               this.environnement.getEnnemis().get(0).setDy(1);
               this.environnement.getEnnemis().get(0).seDeplace();
               break;
           case Q:
               this.environnement.getEnnemis().get(0).setDx(-1);
               this.environnement.getEnnemis().get(0).setDy(0);
               this.environnement.getEnnemis().get(0).seDeplace();
               break;
           case D:
               this.environnement.getEnnemis().get(0).setDx(1);
               this.environnement.getEnnemis().get(0).setDy(0);
               this.environnement.getEnnemis().get(0).seDeplace();
               break;
       }
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

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void creerSpriteEnnemi(Ennemi e) {
        Circle sprite = new Circle(3, Color.RED);
        sprite.setId(e.getId());
        sprite.translateXProperty().bind(e.xProperty());
        sprite.translateYProperty().bind(e.yProperty());
        this.paneJeu.getChildren().add(sprite); // meilleur de mettre le pane au lieu du tilePane, sinon les coordonnées ne marchent pas
    }

}