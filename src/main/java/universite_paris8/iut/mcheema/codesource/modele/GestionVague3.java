package universite_paris8.iut.mcheema.codesource.modele;

import com.sun.source.tree.NewArrayTree;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;

import java.sql.Array;
import java.util.ArrayList;

public class GestionVague3 {
    private final static int TEMPS_ATTENTE = 100;
    private ArrayList<Vague> listeVague;
    private Environnement environnement;
    private int vagueCourante;

    public GestionVague3(Environnement environnement) {
        this.listeVague = new ArrayList<>();
        this.environnement = environnement;
        this.vagueCourante = 0;
    }



    public void lancerVague() {
        if (this.vagueCourante < this.listeVague.size())
            if (this.environnement.getNbTours() % TEMPS_ATTENTE == 0) {
                Vague vague = listeVague.get(vagueCourante);
            }
        }
    }
}
