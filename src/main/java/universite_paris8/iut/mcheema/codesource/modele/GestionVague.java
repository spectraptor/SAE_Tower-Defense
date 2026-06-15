package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

/**
 * La classe GestionVague se tâche de mettre en place et d'organiser
 * les différentes vagues, pour que les ennemis apparaissent et qu'elles se succèdent correctement.
 * C'est cette classe qui s'occupe d'insérer les ennemis à chaque vague dans l'environnement, avec un certain délai.
 * Elle définit également le nombre de vagues maximales.
 */
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
     * Insère les ennemis dans l'environnement (et les retire de la liste d'ennemis dans vague), avec un certain délai.
     */
    public void mettreAJour() {
            if (!this.listeEnnemisVagueCourante().isEmpty())
                if (this.environnement.getNbTours() % TEMPS_ENNEMIS_VAGUE_FRM == 0) {
                    this.environnement.ajouterEnnemi(this.listeEnnemisVagueCourante().remove(0));
        }
    }

    /**
     * Incrémente le numéro de vague pour passer à la vague suivante.
     */
    public void passerNouvelleVague() {
        // Ligne obligatoire : sinon, lorsque la derniere vague est finie,
        // on va à nouveau incrémenter et accéder à un indice qui n'existe pas.
        if (this.getNumVagueCourante() < this.getNbreVagues() - 1) {
            this.environnement.ajouterArgent(100 + this.getNumVagueCourante() * 10);
            this.setNumVagueCourante(this.getNumVagueCourante() + 1);
        }
    }
}
