package universite_paris8.iut.mcheema.codesource.controleur.listeners;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.projectile.*;
import universite_paris8.iut.mcheema.codesource.vue.ProjectileVue;
import universite_paris8.iut.mcheema.codesource.vue.SonVue;

public class ObservateurListeProjectiles implements ListChangeListener<Projectile> {
    private Pane paneJeu;
    private SonVue sonVue;

    public ObservateurListeProjectiles(Pane paneJeu, SonVue sonVue) {
        this.paneJeu = paneJeu;
        this.sonVue = sonVue;
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while(change.next()) {
            for (Projectile projectile : change.getAddedSubList()) {
                ProjectileVue sprite = new ProjectileVue(this.paneJeu,projectile,sonVue);
                sprite.creeSpriteProjectile();
            }

            for (Projectile projectile : change.getRemoved()) {
                if (projectile instanceof MissileTete) {
                    this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + projectile.getId()));
                }
                else if (projectile instanceof MissileZone) {
                    this.sonVue.jouerExplosion();
                    this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + projectile.getId() + "B"));
                    this.paneJeu.lookup("#" + projectile.getId() + "E").setVisible(true);

                    FadeTransition disparaitre = new FadeTransition(Duration.millis(500), this.paneJeu.lookup("#" + projectile.getId() + "E"));
                    RotateTransition tourner = new RotateTransition(Duration.millis(500), this.paneJeu.lookup("#" + projectile.getId() + "E"));
                    disparaitre.setToValue(0);
                    tourner.setByAngle(360);
                    tourner.setOnFinished(e -> this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + projectile.getId() + "E")));
                    disparaitre.play();
                    tourner.play();
                }
                else if (projectile instanceof MissileFragmentation) {
                    this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + projectile.getId() + "F"));
                }
            }
        }
    }
}
