package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.ChevalDeTroie;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.ErreurExecution;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ping;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

/**
 * La class à pour tâche de s'occuper de l'affichage des personnages et de la création de leur sprite.
 * Elle s'occupe d'un ennemi qu'elle reçoit.
 */
public class EnnemiVue {
    private Ennemi ennemi;
    private Pane paneJeu;

    public EnnemiVue(Ennemi ennemi, Pane paneJeu) {
        this.ennemi = ennemi;
        this.paneJeu = paneJeu;
    }

    public void creerSpriteEnnemi() {
        Circle spriteE = new Circle(3, Color.RED);
        if(this.ennemi instanceof Ping) {
            spriteE = new Circle(3, Color.BLUE);
        }
        if(this.ennemi instanceof ChevalDeTroie) {
            spriteE = new Circle(3, Color.GREEN);
        }
        if(this.ennemi instanceof ErreurExecution) {
            spriteE = new Circle(3, Color.BLACK);
        }
        spriteE.setId(ennemi.getId());
        spriteE.translateXProperty().bind(ennemi.xProperty());
        spriteE.translateYProperty().bind(ennemi.yProperty());
        paneJeu.getChildren().add(spriteE);
    }
}
