package universite_paris8.iut.mcheema.codesource.vue;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.batiment.Batiment;

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
        Circle spriteB = new Circle(4, Color.DODGERBLUE);

        spriteB.translateXProperty().bind(this.batiment.xProperty());
        spriteB.translateYProperty().bind(this.batiment.yProperty());

        Circle rayonB = new Circle(this.batiment.getPortee()); // permet de visualiser le rayon de la tour

        /* Style visuel */
        /*
        rayonB.setFill(Color.TRANSPARENT);
        rayonB.setStroke(Color.BLACK);
        rayonB.setStrokeWidth(1.5);
        rayonB.setOpacity(0.1);
        */
        /* Au moment ou le sprite est crée, translateX et translateY sont nuls.
        On est donc obligé de faire un bind, autrement ça ne marche pas. */
        rayonB.centerXProperty().bind(spriteB.translateXProperty());
        rayonB.centerYProperty().bind(spriteB.translateYProperty());

        this.paneJeu.getChildren().add(1, spriteB);
        // this.paneJeu.getChildren().add(rayonB);
    }
}
