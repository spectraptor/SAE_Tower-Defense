package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Base;

public class BaseVue {
    private Pane paneJeu;
    private Base base;

    public BaseVue(Pane paneJeu, Base base) {
        this.paneJeu = paneJeu;
        this.base = base;
    }

    public void creeSpriteBase() {
        Circle spriteBase = new Circle(5, Color.BLACK);
        spriteBase.translateXProperty().bind(this.base.xProperty());
        spriteBase.translateYProperty().bind(this.base.yProperty());
        this.paneJeu.getChildren().add(spriteBase);
    }
}
