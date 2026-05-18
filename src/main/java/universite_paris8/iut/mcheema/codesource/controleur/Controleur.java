package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    private Environnement environnement;

    @FXML
    private TilePane tilePane;

    @FXML
    private Pane panePrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.environnement = new Environnement(640,480);
        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur()* Terrain.PIXEL_TUILLE,this.environnement.getTerrainDeJeu().obtenirHauteur()*Terrain.PIXEL_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(),this.tilePane);
        terrainVue.initialiseTerrainJeu();
        Ennemi bug = new Bogue(400, 100, this.environnement);
        this.environnement.ajouterEnnemi(bug);
        creerSpriteEnnemi(bug);
    }

    public void deplacerEnnemi(KeyEvent keyEvent) {
       switch (keyEvent.getCode()) {
           case Z:
               this.environnement.getEnnemis().get(0).setDx(0);
               this.environnement.getEnnemis().get(0).setDy(-1);
               break;
           case S:
               this.environnement.getEnnemis().get(0).setDx(0);
               this.environnement.getEnnemis().get(0).setDy(1);
               break;
           case Q:
               this.environnement.getEnnemis().get(0).setDx(-1);
               this.environnement.getEnnemis().get(0).setDy(0);
               break;
           case D:
               this.environnement.getEnnemis().get(0).setDx(1);
               this.environnement.getEnnemis().get(0).setDy(0);
               break;
       }
       this.environnement.getEnnemis().get(0).seDeplace();
    }

    public void creerSpriteEnnemi(Ennemi e) {
        Circle sprite = new Circle(3, Color.RED);
        sprite.setId(e.getId());
        sprite.translateXProperty().bind(e.xProperty());
        sprite.translateYProperty().bind(e.yProperty());
        this.panePrincipal.getChildren().add(sprite); // meilleur de mettre le panePrincipal au lieu du tilePane, sinon les coordonnées ne marchent pas
    }


}