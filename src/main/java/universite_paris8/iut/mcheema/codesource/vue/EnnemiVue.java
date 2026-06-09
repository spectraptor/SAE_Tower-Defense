package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

/**
 * La classe à pour tâche de s'occuper de l'affichage des personnages et de la création de leur sprite.
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
        Label labelArgent = new Label(Integer.toString(this.ennemi.getArgentDonne()));
        if(this.ennemi instanceof GrosBogue) {
            spriteE = new Circle(3, Color.BLUE);
        }
        if(this.ennemi instanceof Ping) {
            spriteE = new Circle(3, Color.GREEN);
        }
        if(this.ennemi instanceof ErreurExecution) {
            spriteE = new Circle(3, Color.BLACK);
        }
        spriteE.setId(ennemi.getId());
        spriteE.translateXProperty().bind(ennemi.xProperty());
        spriteE.translateYProperty().bind(ennemi.yProperty());
        labelArgent.translateXProperty().bind(ennemi.xProperty().subtract(7));
        labelArgent.translateYProperty().bind(ennemi.yProperty().subtract(30));
        paneJeu.getChildren().add(1, labelArgent);
        paneJeu.getChildren().add(1, spriteE);
        labelArgent.setTextFill(Color.WHITE);
        labelArgent.setId(this.ennemi.getId() + "A");
        labelArgent.setVisible(false);
    }
}
