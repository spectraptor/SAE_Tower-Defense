package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;

import java.util.ArrayList;

public class Vague {
    private int numeroVague;
    private ArrayList<Ennemi> listeEnnemisVague;
    private Environnement environnement;

    public Vague(int numVague, int numNiv, Environnement env) {
        this.numeroVague = numVague;
        this.listeEnnemisVague = new ArrayList<>();
        this.environnement = env;
        this.initialiseVague(numNiv);
    }


    public ArrayList<Ennemi> getlisteEnnemisVague() {
        return this.listeEnnemisVague;
    }

    public void initialiseVague(int niveau) {
        Niveau niveauChoisi = new Niveau(niveau);
        ArrayList<Point> chemin = null;

        for (int i = 0; i < niveauChoisi.getBases().size(); i++) {
            Point base = niveauChoisi.getBases().get(i);
            Point entree = niveauChoisi.getEntrees().get(i);
            BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), base);
            chemin = bfs.cheminDepuisSource(entree);
        }

        switch (this.numeroVague) {
            case 1:
                // Créer 3 bogues
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, chemin));
                }
                break;
            case 2:
                // Créer 3 bogues, 2 ralentisseur, 1 ping
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, chemin));
                }

                this.listeEnnemisVague.add(new Ralentisseur(this.environnement, chemin));
                this.listeEnnemisVague.add(new Ralentisseur(this.environnement, chemin));

                this.listeEnnemisVague.add(new Ping(this.environnement, chemin));

                break;

            case 3:
                // Créer 5 bogues
                for (int i = 0; i < 5; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, chemin));
                }
                break;
            case 4:
                // Créer 3 bogues, 2 ping, 1 ralentisseur, 2 bosses.
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, chemin));
                }

                this.listeEnnemisVague.add(new Ping(this.environnement, chemin));
                this.listeEnnemisVague.add(new Ping(this.environnement, chemin));

                this.listeEnnemisVague.add(new Ralentisseur(this.environnement, chemin));

                this.listeEnnemisVague.add(new ErreurDeLogique(this.environnement, chemin));
                this.listeEnnemisVague.add(new ErreurDeSyntaxe(this.environnement, chemin));
        }
    }

}
