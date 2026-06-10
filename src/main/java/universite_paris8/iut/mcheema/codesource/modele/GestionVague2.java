package universite_paris8.iut.mcheema.codesource.modele;

import com.sun.source.tree.NewArrayTree;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;
/*
public class GestionVague3 {
    private final static int TEMPS_ATTENTE = 100;
    public final static int MAX_VAGUE = 1;
    private Environnement environnement;
    private int vagueCourante;
    private ArrayList<Ennemi> listeEnnemi;
    private int niveau;

    public GestionVague3(Environnement environnement,int niveau) {
        this.listeEnnemi = new ArrayList<>();
        this.environnement = environnement;
        this.vagueCourante = 0;
        this.niveau = niveau;
        this.initialiseVague();
    }

    public ArrayList<Ennemi> getListeEnnemi() {
        return this.listeEnnemi;
    }

    public void metAJour() {
        if(this.environnement.getNbTours() % TEMPS_ATTENTE == 0) {
            if(!this.listeEnnemi.isEmpty())
                this.environnement.getEnnemis().add(this.listeEnnemi.remove(0));
        }
    }

    public int getVagueCourante() {
        return this.vagueCourante;
    }


    public void initialiseVague() {
        this.vague1();
    }

    public void vague1() {
        this.vagueCourante++;
        Niveau niveauChoisi = new Niveau(niveau);
        for (int i = 0; i < niveauChoisi.getBases().size(); i++) {
            Point base = niveauChoisi.getBases().get(i);
            Point entree = niveauChoisi.getEntrees().get(i);
            BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), base);
            ArrayList<Point> chemin = bfs.cheminDepuisSource(entree);

            this.listeEnnemi.add(new Bogue(this.environnement, chemin));
            this.listeEnnemi.add(new Bogue(this.environnement, chemin));
            this.listeEnnemi.add(new Bogue(this.environnement, chemin));
        }
    }
}


 */