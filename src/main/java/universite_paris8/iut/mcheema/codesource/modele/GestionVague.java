package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class GestionVague {
    private final static int TEMPS_ENNEMIS_VAGUE_FRM = 100;
    private final static int MAX_VAGUES = 4;

    private IntegerProperty numVagueCouranteProperty;
    private Vague[] vagues;
    private Environnement environnement;

    public GestionVague(int numNiv, Environnement env) {
        this.numVagueCouranteProperty = new SimpleIntegerProperty(0);
        this.vagues = new Vague[MAX_VAGUES];

        for (int i = 0; i < MAX_VAGUES; i++) {
            this.vagues[i] = new Vague(i + 1, numNiv, env);
        }

        this.environnement = env;
    }

    public Vague[] getVagues() {
        return this.vagues;
    }

    public int getNbreVagues() {
        return MAX_VAGUES;
    }

    public final IntegerProperty numVagueCouranteProperty() {
        return this.numVagueCouranteProperty;
    }

    public final int getNumVagueCourante() {
        return this.numVagueCouranteProperty.getValue();
    }

    public final void setNumVagueCourante(int val) {
        this.numVagueCouranteProperty.setValue(val);
    }

    public ArrayList<Ennemi> listeEnnemisVagueCourante() {
        return this.vagues[this.getNumVagueCourante()].getlisteEnnemisVague();
    }

    /**
     * Met à jour les ennemis de la vague au fur et à mesure.
     */
    public void mettreAJour() {
            if (!this.listeEnnemisVagueCourante().isEmpty())
                if (this.environnement.getNbTours() % TEMPS_ENNEMIS_VAGUE_FRM == 0) {
                    this.environnement.ajouterEnnemi(this.listeEnnemisVagueCourante().remove(0));
        }
    }

    public void incrementerVagueCourante() {
        // Ligne obligatoire : sinon, lorsque la derniere vague est finie,
        // on va à nouveau incrémenter et accéder à un indice qui n'existe pas.
        if (this.getNumVagueCourante() < this.getNbreVagues() - 1)
            this.setNumVagueCourante(this.getNumVagueCourante() + 1);
    }
}
