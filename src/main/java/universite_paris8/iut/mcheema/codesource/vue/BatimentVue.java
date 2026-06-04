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
        Image img;
        ImageView vue =null;
        if(this.batiment instanceof Compilateur) {
            img  = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/compilateur.png").toExternalForm());
            vue = new ImageView(img);
            vue.translateXProperty().bind(this.batiment.xProperty().subtract(Terrain.TAILLE_TUILLE/2));
            vue.translateYProperty().bind(this.batiment.yProperty().subtract(Terrain.TAILLE_TUILLE/2));
            this.paneJeu.getChildren().add(1, vue );
        }
        else if(this.batiment instanceof Debugger) {
             img = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/debugger.png").toExternalForm());
            vue = new ImageView(img);
            vue.translateXProperty().bind(this.batiment.xProperty().subtract(Terrain.TAILLE_TUILLE/2));
            vue.translateYProperty().bind(this.batiment.yProperty().subtract(Terrain.TAILLE_TUILLE/2));
            this.paneJeu.getChildren().add(1, vue );
        }
        else if(this.batiment instanceof BombeLogique) {
             img = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/bombeLogique.png").toExternalForm());
             vue = new ImageView(img);
             vue.translateXProperty().bind(this.batiment.xProperty().subtract(Terrain.TAILLE_TUILLE/2));
             vue.translateYProperty().bind(this.batiment.yProperty().subtract(Terrain.TAILLE_TUILLE/2));
             this.paneJeu.getChildren().add(1, vue );
        }
        else if(this.batiment instanceof Surcadence) {
             img = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/surcadence.png").toExternalForm());
             vue = new ImageView(img);
             vue.translateXProperty().bind(this.batiment.xProperty().subtract(Terrain.TAILLE_TUILLE/2));
             vue.translateYProperty().bind(this.batiment.yProperty().subtract(Terrain.TAILLE_TUILLE/2));
             this.paneJeu.getChildren().add(1, vue );
        }



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

        vue.setOnMouseClicked(e -> {
            Pane paneInfo = new Pane();
            paneInfo.setId(this.batiment.getId() + "I");
            paneInfo.setPrefWidth(200);
            paneInfo.setPrefHeight(150);

            Label textInfo = new Label();
            textInfo.setWrapText(true);
            textInfo.setPrefWidth(200);
            textInfo.setPrefHeight(150);
            textInfo.setId("textInfoBatiment");
            textInfo.setText(this.batiment.toString());
            textInfo.setPadding(new Insets(5,0,5,15));
            paneInfo.getChildren().add(textInfo);

            Button boutonQuitter = new Button("X");
            boutonQuitter.setTranslateX(163);
            boutonQuitter.setTranslateY(2);
            boutonQuitter.setPrefWidth(30);
            boutonQuitter.setPrefHeight(5);
            paneInfo.getChildren().add(boutonQuitter);
            boutonQuitter.setId("boutonQuitterInfoBat");

            this.paneJeu.getChildren().add(4,paneInfo);

            if(this.batiment.getX() <= this.paneJeu.getPrefWidth()/2) {
                paneInfo.setTranslateX(batiment.getX());
            }
            else {
                paneInfo.setTranslateX(batiment.getX()-paneInfo.getPrefWidth());
            }
            if(this.batiment.getY() <= this.paneJeu.getPrefHeight()/2) {
                paneInfo.setTranslateY(batiment.getY());
            }
            else {
                paneInfo.setTranslateY(batiment.getY()-paneInfo.getPrefHeight());
            }
            boutonQuitter.setOnMouseClicked(event-> {
                this.paneJeu.getChildren().remove(paneInfo);
            });
        });
    }


    private void lier(Image img) {
        ImageView vue = new ImageView(img);
        vue.translateXProperty().bind(this.batiment.xProperty().subtract(Terrain.TAILLE_TUILLE/2));
        vue.translateYProperty().bind(this.batiment.yProperty().subtract(Terrain.TAILLE_TUILLE/2));
        this.paneJeu.getChildren().add(1, vue );
    }
}
