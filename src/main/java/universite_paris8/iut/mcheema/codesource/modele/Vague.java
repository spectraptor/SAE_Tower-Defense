package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * La classe Vague s'occupe de la gestion individuelle des vagues.
 * Elle a pour but de de créer des vagues, composées d'ennemis précisément choisis
 * qui vont être capable de se déplacer vers la base et posséder un chemin.
 * Le système de vague doit être automatisée et auto-fonctionnel.
 */
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
        ArrayList<ArrayList<Point>> chemins = new ArrayList<>();

        for (int i = 0; i < niveauChoisi.getBases().size(); i++) {
            Point base = niveauChoisi.getBases().get(i);
            Point entree = niveauChoisi.getEntrees().get(i);
            BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), base);
            ArrayList<Point> chemin = bfs.cheminDepuisSource(entree);
            chemins.add(chemin);
        }


        switch (this.numeroVague) {
            case 1:
                // Créer 3 bogues
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }
                break;
            case 2:
                // Créer 3 bogues, 2 ralentisseur, 1 ping
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }

                this.listeEnnemisVague.add(new Ralentisseur(this.environnement, this.choisiAleatoirementChemin(chemins)));
                this.listeEnnemisVague.add(new Ralentisseur(this.environnement, this.choisiAleatoirementChemin(chemins)));

                this.listeEnnemisVague.add(new Ping(this.environnement, this.choisiAleatoirementChemin(chemins)));

                break;

            case 3:
                // Créer 5 bogues
                for (int i = 0; i < 5; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }
                break;
            case 4:
                // Créer 3 bogues, 2 ping, 1 ralentisseur, 2 bosses.
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }

                this.listeEnnemisVague.add(new Ping(this.environnement, this.choisiAleatoirementChemin(chemins)));
                this.listeEnnemisVague.add(new Ping(this.environnement, this.choisiAleatoirementChemin(chemins)));

                this.listeEnnemisVague.add(new Ralentisseur(this.environnement, this.choisiAleatoirementChemin(chemins)));

                this.listeEnnemisVague.add(new ErreurDeLogique(this.environnement, this.choisiAleatoirementChemin(chemins)));
                this.listeEnnemisVague.add(new ErreurDeSyntaxe(this.environnement, this.choisiAleatoirementChemin(chemins)));

        }
    }

    public boolean vagueEstTerminee() {
        /* Les deux conditions sont obligatoires :
            Si on regarde uniquement la première, alors une nouvelle vague commencera lorsque tous les ennemis de la précédente vague
            ont été placées, sans regarder si les ennemis de la vague actuelle sont morts.
            Si on regarde uniquement la deuxième, alors les vagues commenceront les unes à la suite car la liste d'ennemis sera toujours vide.
            Il faut donc bien regarder que tous les ennemis de la vague ont bien été placés, et qu'ils sont morts.
         */
        return this.listeEnnemisVague.isEmpty() && this.environnement.getEnnemis().isEmpty();
    }


    public ArrayList<Point> choisiAleatoirementChemin(ArrayList<ArrayList<Point>> chemins) {
        int numeroChemin = (int) (Math.random() * chemins.size());
        return chemins.get(numeroChemin);
    }

}
