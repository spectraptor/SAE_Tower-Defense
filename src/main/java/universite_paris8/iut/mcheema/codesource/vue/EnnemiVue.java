package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

/**
 * La class à pour tâche de s'occuper de l'affichage des personnages et de la création de leur sprite.
 * Elle s'occupe d'un ennemi qu'elle reçoit.
 */
public class EnnemiVue {
    private Ennemi ennemi;
    private Pane paneJeu;

    public EnnemiVue(Ennemi em, Pane paneJ) {
        this.ennemi = em;
        this.paneJeu = paneJ;
    }

    public void creerSpriteEnnemi() {
        Circle spriteE = new Circle(3, Color.RED);
        spriteE.setId(ennemi.getId());
        spriteE.translateXProperty().bind(ennemi.xProperty());
        spriteE.translateYProperty().bind(ennemi.yProperty());
        paneJeu.getChildren().add(spriteE);
    }
}
