package universite_paris8.iut.mcheema.codesource.controleur.listeners;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;
import universite_paris8.iut.mcheema.codesource.vue.ProjectileVue;

public class ObservateurListeProjectiles implements ListChangeListener<Projectile> {
    private Pane paneJeu;
    private final AudioClip explosion;

    public ObservateurListeProjectiles(Pane paneJeu) {
        this.paneJeu = paneJeu;
        this.explosion = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/explosion.mp3").toExternalForm());
        this.explosion.setVolume(0.3);
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while(change.next()) {
            for (Projectile projectile : change.getAddedSubList()) {
                ProjectileVue sprite = new ProjectileVue(this.paneJeu,projectile);
                sprite.creeSpriteProjectile();
            }

            for (Projectile projectile : change.getRemoved()) {
                this.paneJeu.lookup("#" + projectile.getId()).translateXProperty().unbind();
                this.paneJeu.lookup("#" + projectile.getId()).translateYProperty().unbind();
                this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + projectile.getId()));

                if (projectile instanceof MissileZone) {
                    explosion.play();
                    this.paneJeu.lookup("#" + projectile.getId() + "P").translateXProperty().unbind();
                    this.paneJeu.lookup("#" + projectile.getId() + "P").translateYProperty().unbind();
                    this.paneJeu.lookup("#" + projectile.getId() + "P").setVisible(true);

                    FadeTransition disparaitre = new FadeTransition(Duration.millis(500), this.paneJeu.lookup("#" + projectile.getId() + "P"));
                    RotateTransition tourner = new RotateTransition(Duration.millis(500), this.paneJeu.lookup("#" + projectile.getId() + "P"));
                    disparaitre.setToValue(0);
                    tourner.setByAngle(360);
                    tourner.setOnFinished(e -> this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + projectile.getId() + "P")));
                    disparaitre.play();
                    tourner.play();
                }
            }
        }
    }
}
