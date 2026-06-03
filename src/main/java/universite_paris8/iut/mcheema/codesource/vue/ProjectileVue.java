package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;
/**
 * La classe à pour tâche de s'occuper de l'affichage des projectiles et de la création de leur sprite.
 *
 */
public class ProjectileVue {
    private Pane paneJeu;
    private Projectile projectile;

    public ProjectileVue(Pane paneJeu,Projectile projectile) {
        this.paneJeu = paneJeu;
        this.projectile = projectile;
    }

    public void creeSpriteProjectile() {
        Circle sprite = new Circle(2, Color.MAROON);
        sprite.setId(projectile.getId());
        sprite.translateXProperty().bind(projectile.xProperty());
        sprite.translateYProperty().bind(projectile.yProperty());
        this.paneJeu.getChildren().add(sprite);

        if (this.projectile instanceof MissileZone) {
            Circle rayonB = new Circle(((MissileZone) this.projectile).getPortee());

            /* Style visuel */
            rayonB.setFill(Color.TRANSPARENT);
            rayonB.setStroke(Color.BLACK);
            rayonB.setStrokeWidth(1.5);

        /* Au moment ou le sprite est crée, translateX et translateY sont nuls.
        On est donc obligé de faire un bind, autrement ça ne marche pas. */
            rayonB.centerXProperty().bind(sprite.translateXProperty());
            rayonB.centerYProperty().bind(sprite.translateYProperty());
            this.paneJeu.getChildren().add(rayonB);
        }
        this.paneJeu.getChildren().add(1, sprite);
    }
}
