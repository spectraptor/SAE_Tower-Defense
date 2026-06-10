package universite_paris8.iut.mcheema.codesource.controleur.listeners;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
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
                this.paneJeu.lookup("#"+ennemi.getId()+"A").translateXProperty().unbind();
                this.paneJeu.lookup("#"+ennemi.getId()+"A").translateYProperty().unbind();
                this.paneJeu.getChildren().remove(this.paneJeu.lookup("#"+ennemi.getId()));

                if (!ennemi.aAtteintDestination()) {
                    this.paneJeu.lookup("#" + ennemi.getId() + "A").setVisible(true);
                    TranslateTransition monter = new TranslateTransition(Duration.millis(2000), this.paneJeu.lookup("#" + ennemi.getId() + "A"));
                    monter.setByY(-80);
                    FadeTransition disparaitre = new FadeTransition(Duration.millis(2000), this.paneJeu.lookup("#" + ennemi.getId() + "A"));
                    disparaitre.setToValue(0.0);
                    monter.setOnFinished(e -> this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + ennemi.getId() + "A")));
                    monter.play();
                    disparaitre.play();
                }
            }
        }
    }
}