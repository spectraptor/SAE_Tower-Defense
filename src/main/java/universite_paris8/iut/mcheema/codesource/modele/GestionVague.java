package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class GestionVague {
    private IntegerProperty numeroVagueProperty;
    private Environnement environnement;
    private int nbreEnnemiApparu;
    private int niveau;
    private ArrayList<Ennemi> listeEnnemisActuel;

    public GestionVague(Environnement environnement,int niveau) {
        this.numeroVagueProperty = new SimpleIntegerProperty(1);
        this.environnement = environnement;
        this.nbreEnnemiApparu = 0;
        this.niveau = niveau;
        this.listeEnnemisActuel = new ArrayList<>();
    }

    public final int getNumeroVague() {
        return this.numeroVagueProperty.getValue();
    }

    public final void setNumeroVague(int numero) {
        this.numeroVagueProperty.setValue(numero);
    }

    public final IntegerProperty numeroVaguePorperty() {
        return this.numeroVagueProperty;
    }

    public void ajouterEnnemi() {
        if(this.nbreEnnemiApparu <= this.listeEnnemisActuel.size() -1) {
            Niveau niveauChoisi = new Niveau(this.niveau);

            for (int i = 0; i < niveauChoisi.getBases().size(); i++) {
                Point base = niveauChoisi.getBases().get(i);
                Point entree = niveauChoisi.getEntrees().get(i);
                BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), base);
                ArrayList<Point> chemin = bfs.cheminDepuisSource(entree);

                for (int j = 0; j < 10; j++) {
                    this.environnement.ajouterEnnemi(new Bogue(this.environnement, chemin));
                    this.nbreEnnemiApparu++;
                }
            }
        }

    }


}
