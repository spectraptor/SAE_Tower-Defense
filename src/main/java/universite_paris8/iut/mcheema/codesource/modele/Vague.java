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
                // Créer 7
                for (int i = 0; i < 7; i++) {
                    this.listeEnnemisVague.add(new Ping(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }
                break;

            case 3:
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Ping(this.environnement, this.choisiAleatoirementChemin(chemins)));
                    this.listeEnnemisVague.add(new Bogue(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }
                break;
            case 4:
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }
                this.listeEnnemisVague.add(new ChevalDeTroie(this.environnement, this.choisiAleatoirementChemin(chemins)));
                break;
            case 5:
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new ChevalDeTroie(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }
                this.listeEnnemisVague.add(new DroneEspion(this.environnement, this.choisiAleatoirementChemin(chemins)));
                break;
            case 6:
                for (int i = 0; i < 5; i++) {
                    this.listeEnnemisVague.add(new DroneEspion(this.environnement, this.choisiAleatoirementChemin(chemins)));
                    this.listeEnnemisVague.add(new ChevalDeTroie(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }
                break;
            case 7:
                for (int i = 0; i < 3; i++) {
                    this.listeEnnemisVague.add(new Bogue(this.environnement, this.choisiAleatoirementChemin(chemins)));
                    this.listeEnnemisVague.add(new GrosBogue(this.environnement, this.choisiAleatoirementChemin(chemins)));
                }
                this.listeEnnemisVague.add(new Ralentisseur(this.environnement, this.choisiAleatoirementChemin(chemins)));
                this.listeEnnemisVague.add(new Ralentisseur(this.environnement, this.choisiAleatoirementChemin(chemins)));
                break;
            case 8:
                this.listeEnnemisVague.add(new ErreurDeLogique(this.environnement, this.choisiAleatoirementChemin(chemins)));
                this.listeEnnemisVague.add(new ErreurDeSyntaxe(this.environnement, this.choisiAleatoirementChemin(chemins)));
                this.listeEnnemisVague.add(new ErreurExecution(this.environnement, this.choisiAleatoirementChemin(chemins)));
        }
    }

    public ArrayList<Point> choisiAleatoirementChemin(ArrayList<ArrayList<Point>> chemins) {
        int numeroChemin = (int) (Math.random() * chemins.size());
        return chemins.get(numeroChemin);
    }

}
