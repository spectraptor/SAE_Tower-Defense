package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileFragmentation;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;
/**
 * La classe à pour tâche de s'occuper de l'affichage des projectiles et de la création de leur sprite.
 *
 */
public class ProjectileVue {
    private Pane paneJeu;
    private Projectile projectile;
    private SonVue sonVue;

    public ProjectileVue(Pane paneJeu,Projectile projectile) {
        this.paneJeu = paneJeu;
        this.projectile = projectile;
        this.sonVue = new SonVue();
    }

    public void creeSpriteProjectile() {

        Image imgProjectile0 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/projectile0.png").toExternalForm());;
        Image imgProjectile1 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/projectile1.png").toExternalForm());
        Image imgExplosion = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/explosion.png").toExternalForm());
        Image imgFragmentation = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/fragmentation.png").toExternalForm());
        Image imgBombe = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/bombeLogique.png").toExternalForm());

        ImageView imageViewprojectile;


        if (this.projectile instanceof  MissileTete) {
            this.sonVue.jouerMissileTete();
            if (Math.random() < 0.5) {
                imageViewprojectile = new ImageView(imgProjectile0);
            } else {
                imageViewprojectile = new ImageView(imgProjectile1);
            }
            imageViewprojectile.translateXProperty().bind(this.projectile.xProperty().subtract(4));
            imageViewprojectile.translateYProperty().bind(this.projectile.yProperty().subtract(4));
            this.paneJeu.getChildren().add(1, imageViewprojectile);
            imageViewprojectile.setId(this.projectile.getId());
        }
        else if (this.projectile instanceof MissileZone) {
            this.sonVue.jouerMissileZone();
            ImageView vueBombe = new ImageView(imgBombe);
            ImageView vueExplosion = new ImageView(imgExplosion);
            vueBombe.translateXProperty().bind(this.projectile.xProperty().subtract(16)); // 32 px img
            vueBombe.translateYProperty().bind(this.projectile.yProperty().subtract(16));


            vueExplosion.translateXProperty().bind(this.projectile.xProperty().subtract(20)); // 40 px img
            vueExplosion.translateYProperty().bind(this.projectile.yProperty().subtract(20));
            this.paneJeu.getChildren().add(1, vueBombe);
            vueBombe.setId(this.projectile.getId() + "B");
            this.paneJeu.getChildren().add(1, vueExplosion);
            vueExplosion.setId(this.projectile.getId() + "E");
            vueExplosion.setVisible(false);
        }
        else if (this.projectile instanceof MissileFragmentation) {
            this.sonVue.jouerMissileFragmentation();
            ImageView vueFragmentation = new ImageView(imgFragmentation);
            vueFragmentation.translateXProperty().bind(this.projectile.xProperty().subtract(10)); // 20 px img
            vueFragmentation.translateYProperty().bind(this.projectile.yProperty().subtract(10));
            this.paneJeu.getChildren().add(1, vueFragmentation);
            vueFragmentation.setId(this.projectile.getId() + "F");
        }
    }
}
