package universite_paris8.iut.mcheema.codesource.controleur.listeners;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.mcheema.codesource.modele.batiment.*;
import universite_paris8.iut.mcheema.codesource.vue.BatimentVue;

import java.awt.*;

public class ObservateurListeBatiments implements ListChangeListener<Batiment> {
    private Pane paneJeu;

    public ObservateurListeBatiments(Pane paneJeu) {
        this.paneJeu = paneJeu;
    }

    @Override
    public void onChanged(Change<? extends Batiment> change) {
        while (change.next()) {
            for (Batiment batiment : change.getAddedSubList()) {
                BatimentVue batimentVue = new BatimentVue(batiment, this.paneJeu);
                batimentVue.creerSpriteBatiment();
            }

            for (Batiment batiment : change.getRemoved()) {
                this.paneJeu.lookup("#" + batiment.getId()).translateXProperty().unbind();
                this.paneJeu.lookup("#" + batiment.getId()).translateYProperty().unbind();
                this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + batiment.getId()));
            }
        }
    }
}
