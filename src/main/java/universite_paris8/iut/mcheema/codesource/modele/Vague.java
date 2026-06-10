package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class Vague {
    private int numeroVague;
    private ArrayList<Ennemi> listeEnnemisVague;
    private Environnement environnement;


    public Vague(int vague,Environnement env,int numeroNiveau) {
        this.numeroVague = vague;
        this.listeEnnemisVague = new ArrayList<>();
        this.environnement = env;
        this.initialiseVague(numeroNiveau);
    }

    public void initialiseVague(int numeroNiveau) {
        switch (this.numeroVague) {
            case 0:
                Niveau niveauChoisi = new Niveau(numeroNiveau);
                for (int i = 0; i < niveauChoisi.getBases().size(); i++) {
                    Point base = niveauChoisi.getBases().get(i);
                    Point entree = niveauChoisi.getEntrees().get(i);
                    BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), base);
                    ArrayList<Point> chemin = bfs.cheminDepuisSource(entree);
                    this.listeEnnemisVague.add(new Bogue(this.environnement, chemin));
                    this.listeEnnemisVague.add(new Bogue(this.environnement, chemin));
                    this.listeEnnemisVague.add(new Bogue(this.environnement, chemin));
                    System.out.println("tete");;
                }
        }
    }


    public ArrayList<Ennemi> getListeEnnemisVague() {
        return this.listeEnnemisVague;
    }
}
