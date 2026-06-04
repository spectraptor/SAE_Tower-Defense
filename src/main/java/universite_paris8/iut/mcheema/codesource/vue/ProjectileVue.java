package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
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

        if (Math.random() < 0.5) {
            Image imgProjectile0 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/projectile0.png").toExternalForm());
            ImageView vueProjectile0 = new ImageView(imgProjectile0);
            vueProjectile0.translateXProperty().bind(this.projectile.xProperty().subtract(4));
            vueProjectile0.translateYProperty().bind(this.projectile.yProperty().subtract(4));
            this.paneJeu.getChildren().add(1, vueProjectile0);
            vueProjectile0.setId(this.projectile.getId());
        } else {
            Image imgProjectile1 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/projectile1.png").toExternalForm());
            ImageView vueProjectile1 = new ImageView(imgProjectile1);
            vueProjectile1.translateXProperty().bind(this.projectile.xProperty().subtract(4));
            vueProjectile1.translateYProperty().bind(this.projectile.yProperty().subtract(4));
            this.paneJeu.getChildren().add(1, vueProjectile1);
            vueProjectile1.setId(this.projectile.getId());
        }
        /*
        Circle sprite = new Circle(2, Color.MAROON);
        sprite.setId(projectile.getId());
        sprite.translateXProperty().bind(projectile.xProperty());
        sprite.translateYProperty().bind(projectile.yProperty());
        this.paneJeu.getChildren().add(1,sprite);
*/
        if (this.projectile instanceof MissileZone) {
            Image imgExplosion = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/explosion.png").toExternalForm());
            ImageView vueExplosion = new ImageView(imgExplosion);
            vueExplosion.translateXProperty().bind(this.projectile.xProperty().subtract(20)); // 40 px diametre
            vueExplosion.translateYProperty().bind(this.projectile.yProperty().subtract(20));
            this.paneJeu.getChildren().add(1, vueExplosion);
            vueExplosion.setId(this.projectile.getId() + "P");
            vueExplosion.setVisible(false);

            /* Style visuel */
        /*
            rayonB.setFill(Color.TRANSPARENT);
            rayonB.setStroke(Color.BLACK);
            rayonB.setStrokeWidth(0.5);

            rayonB.centerXProperty().bind(sprite.translateXProperty());
            rayonB.centerYProperty().bind(sprite.translateYProperty());
            rayonB.setId(projectile.getId()+"P");
            this.paneJeu.getChildren().add(1,rayonB);
        }
         */
        }
    }
}
