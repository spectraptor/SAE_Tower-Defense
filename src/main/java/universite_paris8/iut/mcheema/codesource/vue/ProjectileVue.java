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
        Image imgProjectile0 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/projectile0.png").toExternalForm());;
        Image imgProjectile1 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/projectile1.png").toExternalForm());
        Image imgExplosion = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/explosion.png").toExternalForm());
        ImageView imageViewprojectile;
        if (Math.random() < 0.5) {
            imageViewprojectile = new ImageView(imgProjectile0);
        } else {
            imageViewprojectile = new ImageView(imgProjectile1);
        }
        imageViewprojectile.translateXProperty().bind(this.projectile.xProperty().subtract(4));
        imageViewprojectile.translateYProperty().bind(this.projectile.yProperty().subtract(4));
        this.paneJeu.getChildren().add(1, imageViewprojectile);
        imageViewprojectile.setId(this.projectile.getId());

        if (this.projectile instanceof MissileZone) {
            ImageView vueExplosion = new ImageView(imgExplosion);
            vueExplosion.translateXProperty().bind(this.projectile.xProperty().subtract(20)); // 40 px diametre
            vueExplosion.translateYProperty().bind(this.projectile.yProperty().subtract(20));
            this.paneJeu.getChildren().add(1, vueExplosion);
            vueExplosion.setId(this.projectile.getId() + "P");
            vueExplosion.setVisible(false);
        }
    }
}
