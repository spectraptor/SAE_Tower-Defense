package universite_paris8.iut.mcheema.codesource.controleur.listeners;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.vue.EnnemiVue;
import universite_paris8.iut.mcheema.codesource.vue.SonVue;

public class ObservateurListeEnnemis implements ListChangeListener<Ennemi> {
    private Pane paneJeu;
    private SonVue sonVue;

    public ObservateurListeEnnemis(Pane paneJeu) {
        this.paneJeu = paneJeu;
        this.sonVue = new SonVue();
    }
    @Override
    public void onChanged(Change<?extends Ennemi> change) {
        while (change.next()) {
            for(Ennemi ennemi : change.getAddedSubList()) {
                EnnemiVue ennemiVue = new EnnemiVue(ennemi,this.paneJeu);
                ennemiVue.creerSpriteEnnemi();

                this.sonVue.jouerApparition();
            }

            for (Ennemi ennemi : change.getRemoved()) {
                // this.paneJeu.lookup("#"+ennemi.getId()).translateXProperty().unbind();
                // this.paneJeu.lookup("#"+ennemi.getId()).translateYProperty().unbind();
                // this.paneJeu.lookup("#"+ennemi.getId()+"A").translateXProperty().unbind();
                this.paneJeu.lookup("#"+ennemi.getId()+"A").translateYProperty().unbind();
                RotateTransition tournerEnnemi = new RotateTransition(Duration.millis(600), this.paneJeu.lookup("#" + ennemi.getId()));
                FadeTransition disparaitreEnnemi = new FadeTransition(Duration.millis(1000), this.paneJeu.lookup("#" + ennemi.getId()));
                tournerEnnemi.setByAngle(90);
                disparaitreEnnemi.setToValue(0.0);

                disparaitreEnnemi.setOnFinished(e -> this.paneJeu.getChildren().remove(this.paneJeu.lookup("#"+ennemi.getId())));
                tournerEnnemi.play();
                disparaitreEnnemi.play();

                this.sonVue.jouerMort();

                if (!ennemi.aAtteintDestination()) {
                    this.paneJeu.lookup("#" + ennemi.getId() + "A").setVisible(true);
                    TranslateTransition monterLabelArgent = new TranslateTransition(Duration.millis(2000), this.paneJeu.lookup("#" + ennemi.getId() + "A"));
                    FadeTransition disparaitreLabelArgent = new FadeTransition(Duration.millis(2000), this.paneJeu.lookup("#" + ennemi.getId() + "A"));
                    monterLabelArgent.setByY(-80);
                    disparaitreLabelArgent.setToValue(0);

                    monterLabelArgent.setOnFinished(e -> this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + ennemi.getId() + "A")));
                    monterLabelArgent.play();
                    disparaitreLabelArgent.play();
                }
            }
        }
    }
}