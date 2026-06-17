package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.mcheema.codesource.modele.projectile.*;

/**
 * La classe à pour tâche de s'occuper de l'affichage des projectiles et de la création de leur sprite.
 *
 */
public class ProjectileVue {
    private Pane paneJeu;
    private Projectile projectile;
    private SonVue sonVue;

    public ProjectileVue(Pane paneJeu,Projectile projectile, SonVue sonVue) {
        this.paneJeu = paneJeu;
        this.projectile = projectile;
        this.sonVue = sonVue;
    }

    public void creeSpriteProjectile() {

        Image imgProjectile0 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/projectile0.png").toExternalForm());
        Image imgProjectile1 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/projectile1.png").toExternalForm());
        Image imgExplosion = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/explosion.png").toExternalForm());
        Image imgFragmentation = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/fragmentation.png").toExternalForm());
        Image imgBombe = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/projectiles/bombeLogique.png").toExternalForm());

        if (this.projectile instanceof  MissileTete) {
            this.sonVue.jouerMissileTete();
            ImageView vueTete;
            if (Math.random() < 0.5) {
                vueTete = new ImageView(imgProjectile0);
                this.attacheSprite(vueTete, 4, "");
            } else {
                vueTete = new ImageView(imgProjectile1);
                this.attacheSprite(vueTete, 4, "");
            }
        }
        else if (this.projectile instanceof MissileZone) {
            this.sonVue.jouerMissileZone();
            ImageView vueBombe = new ImageView(imgBombe);
            ImageView vueExplosion = new ImageView(imgExplosion);

            this.attacheSprite(vueBombe, 16, "B");
            this.attacheSprite(vueExplosion, 20, "E");
            vueExplosion.setVisible(false);
        }
        else if (this.projectile instanceof MissileFragmentation) {
            this.sonVue.jouerMissileFragmentation();
            ImageView vueFragmentation = new ImageView(imgFragmentation);
            this.attacheSprite(vueFragmentation, 10, "F");
        }
    }

    /**
     * Permet d'attacher un sprite au pane du jeu
     * @param vueProjectile l'imageView correspondant à l'image du projectile
     * @param decalage le décalage horizontal et vertical à mettre à l'image
     * @param identificateur le suffixe utilisée pour l'id de vueProjectile.
     */
    public void attacheSprite(ImageView vueProjectile, int decalage, String identificateur) {
        vueProjectile.translateXProperty().bind(this.projectile.xProperty().subtract(decalage));
        vueProjectile.translateYProperty().bind(this.projectile.yProperty().subtract(decalage));
        this.paneJeu.getChildren().add(1, vueProjectile);
        vueProjectile.setId(this.projectile.getId() + identificateur);
    }
}
