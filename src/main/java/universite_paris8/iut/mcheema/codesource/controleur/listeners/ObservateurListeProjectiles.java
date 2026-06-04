package universite_paris8.iut.mcheema.codesource.controleur.listeners;

import javafx.animation.PauseTransition;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;
import universite_paris8.iut.mcheema.codesource.vue.ProjectileVue;

public class ObservateurListeProjectiles implements ListChangeListener<Projectile> {
    private Pane paneJeu;

    public ObservateurListeProjectiles(Pane paneJeu) {
        this.paneJeu = paneJeu;
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while(change.next()) {
            for (Projectile projectile : change.getAddedSubList()) {
                ProjectileVue sprite = new ProjectileVue(this.paneJeu,projectile);
                sprite.creeSpriteProjectile();
            }

            for (Projectile projectile : change.getRemoved()) {
                this.paneJeu.lookup("#"+projectile.getId()).translateXProperty().unbind();
                this.paneJeu.lookup("#"+projectile.getId()).translateYProperty().unbind();
                this.paneJeu.getChildren().remove(this.paneJeu.lookup("#"+projectile.getId()));

                if (projectile instanceof MissileZone) {
                    this.paneJeu.lookup("#" + projectile.getId() + "P").translateXProperty().unbind();
                    this.paneJeu.lookup("#" + projectile.getId() + "P").translateYProperty().unbind();
                    this.paneJeu.lookup("#" + projectile.getId() + "P").setVisible(true);
                    PauseTransition pause = new PauseTransition(Duration.millis(500));
                    pause.setOnFinished(e -> this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + projectile.getId() + "P")));
                    pause.play();
                }
            }
        }
    }
}
