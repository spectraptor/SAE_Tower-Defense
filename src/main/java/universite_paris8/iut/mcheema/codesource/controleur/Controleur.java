package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.Ennemi;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.*;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;
import java.net.URL;
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
        Ennemi bug = new Bogue(400, 100, this.environnement); // (560, 140) si on veut essayer qu'il atteigne la fin
        this.environnement.ajouterEnnemi(bug);
        creerSpriteEnnemi(bug);
        terrainVue.afficheTerrainJeu();
        this.initAnimation();
        this.gameLoop.play();

    }

    private void initAnimation() {

        Ennemi premierE = this.environnement.getEnnemis().get(0);
        premierE.attribuerDirectionAleatoire();

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                ev -> {
                    environnement.unTour();
                    /*
                    if (environnement.ennemiSort()) {
                        gameLoop.stop();
                    }
                    */

                }
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