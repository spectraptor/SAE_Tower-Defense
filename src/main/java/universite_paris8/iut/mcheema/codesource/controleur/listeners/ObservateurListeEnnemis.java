package universite_paris8.iut.mcheema.codesource.controleur.listeners;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.vue.EnnemiVue;

public class ObservateurListeEnnemis implements ListChangeListener<Ennemi> {
    private Pane paneJeu;

    public ObservateurListeEnnemis(Pane paneJeu) {
        this.paneJeu = paneJeu;
    }
    @Override
    public void onChanged(Change<?extends Ennemi> change) {
        while (change.next()) {
            for(Ennemi ennemi : change.getAddedSubList()) {
                EnnemiVue ennemiVue = new EnnemiVue(ennemi,this.paneJeu);
                ennemiVue.creerSpriteEnnemi();
            }

            for (Ennemi ennemi : change.getRemoved()) {
                this.paneJeu.lookup("#"+ennemi.getId()).translateXProperty().unbind();
                this.paneJeu.lookup("#"+ennemi.getId()).translateYProperty().unbind();
                this.paneJeu.getChildren().remove(this.paneJeu.lookup("#"+ennemi.getId()));
            }
        }
    }
}