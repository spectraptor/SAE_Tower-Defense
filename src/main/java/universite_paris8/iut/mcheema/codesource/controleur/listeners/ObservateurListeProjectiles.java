package universite_paris8.iut.mcheema.codesource.controleur.listeners;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
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
            }
        }
    }
}
