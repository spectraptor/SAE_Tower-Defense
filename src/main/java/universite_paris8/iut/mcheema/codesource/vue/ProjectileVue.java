package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

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
            rayonB.setStrokeWidth(0.5);

            rayonB.centerXProperty().bind(sprite.translateXProperty());
            rayonB.centerYProperty().bind(sprite.translateYProperty());
            rayonB.setId(projectile.getId()+"P");
            this.paneJeu.getChildren().add(rayonB);
        }
    }
}
