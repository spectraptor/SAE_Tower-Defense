package universite_paris8.iut.mcheema.codesource.modele;

import javafx.collections.ListChangeListener;
import universite_paris8.iut.mcheema.codesource.controleur.Controleur;

public class ObservateurListeEnnemis implements ListChangeListener<Ennemi> {
    private Controleur controleur;

    public ObservateurListeEnnemis(Controleur controleur) {
        this.controleur = controleur;
    }
    @Override
    public void onChanged(Change<?extends Ennemi> change) {
        while (change.next()) {
            for (Ennemi ennemi : change.getRemoved()) {
                this.controleur.getPaneJeu().lookup("#"+ennemi.getId()).translateXProperty().unbind();
                this.controleur.getPaneJeu().lookup("#"+ennemi.getId()).translateYProperty().unbind();
                this.controleur.getPaneJeu().getChildren().remove(this.controleur.getPaneJeu().lookup("#"+ennemi.getId()));
            }
        }
    }
}
