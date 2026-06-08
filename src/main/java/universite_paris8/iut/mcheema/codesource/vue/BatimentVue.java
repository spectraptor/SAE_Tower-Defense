package universite_paris8.iut.mcheema.codesource.vue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.batiment.*;

/**
 * La classe BatimentVue s'occupe de l'affichage des bâtiments, en affichant leurs sprites, leurs projectiles, etc.
 */
public class BatimentVue {
    private Batiment batiment;
    private Pane paneJeu;

    public BatimentVue(Batiment bat, Pane paneJ) {
        this.batiment = bat;
        this.paneJeu = paneJ;
    }

    public void creerSpriteBatiment() {
        Image imgCompilateur = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/compilateur.png").toExternalForm());;
        Image imgCloud = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/cloud.png").toExternalForm());
        Image imgDebugger = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/debugger.png").toExternalForm());
        Image imgBombeLogique = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/bombeLogique.png").toExternalForm());
        Image imgSurcadence = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/surcadence.png").toExternalForm());
        Image imgRAM = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/ram.png").toExternalForm());
        ImageView imgVueBatiment;
        if(this.batiment instanceof Compilateur) {
            imgVueBatiment = new ImageView(imgCompilateur);
        }
        else if(this.batiment instanceof Cloud) {
            imgVueBatiment = new ImageView(imgCloud);
        }
        else if(this.batiment instanceof Debugger) {
            imgVueBatiment = new ImageView(imgDebugger);
        }
        else if(this.batiment instanceof BombeLogique) {
            imgVueBatiment = new ImageView(imgBombeLogique);
        }
        else if (this.batiment instanceof Surcadence) {
            imgVueBatiment = new ImageView(imgSurcadence);
        }
        else {
            imgVueBatiment = new ImageView(imgRAM);
        }
        imgVueBatiment.setId(this.batiment.getId());
        imgVueBatiment.translateXProperty().bind(this.batiment.xProperty().subtract(Terrain.TAILLE_TUILLE/2));
        imgVueBatiment.translateYProperty().bind(this.batiment.yProperty().subtract(Terrain.TAILLE_TUILLE/2));
        this.paneJeu.getChildren().add(1, imgVueBatiment);



        // Circle spriteB = new Circle(4, Color.DODGERBLUE);

        // spriteB.translateXProperty().bind(this.batiment.xProperty());
        // spriteB.translateYProperty().bind(this.batiment.yProperty());
        // Circle rayonB = new Circle(this.batiment.getPortee()); // permet de visualiser le rayon de la tour

        /* Style visuel */
        /*
        rayonB.setFill(Color.TRANSPARENT);
        rayonB.setStroke(Color.BLACK);
        rayonB.setStrokeWidth(1.5);
        rayonB.setOpacity(0.1);
        */
        /* Au moment ou le sprite est crée, translateX et translateY sont nuls.
        On est donc obligé de faire un bind, autrement ça ne marche pas. */
        // rayonB.centerXProperty().bind(spriteB.translateXProperty());
        // rayonB.centerYProperty().bind(spriteB.translateYProperty());

        // this.paneJeu.getChildren().add(1, spriteB);
        // this.paneJeu.getChildren().add(rayonB);

        //INFO SUR LA TOUR

        imgVueBatiment.setOnMouseClicked(e -> {
            InfoBatimentVue infoBatimentVue = new InfoBatimentVue(this.batiment,this.paneJeu);
            infoBatimentVue.afficheInfoBatiment();
        });
    }
}
