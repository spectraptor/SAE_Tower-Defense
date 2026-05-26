package universite_paris8.iut.mcheema.codesource.vue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Batiment;

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
        Circle spriteB = new Circle(4);
        spriteB.translateXProperty().bind(this.batiment.xProperty());
        spriteB.translateYProperty().bind(this.batiment.yProperty());
        this.paneJeu.getChildren().add(spriteB);
    }
}
