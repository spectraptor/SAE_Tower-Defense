package universite_paris8.iut.mcheema.codesource.modele;
/*

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class GestionVague {
    private final static int TEMPS_ATTENTE = 200;
    private IntegerProperty numeroVagueProperty;
    private Environnement environnement;
    private int nbreEnnemiApparu;
    private ArrayList<Ennemi> ennemisASpawn;
    private int niveau;
    private boolean vagueCouranteFini;

    public GestionVague(Environnement environnement,int niveau) {
        this.numeroVagueProperty = new SimpleIntegerProperty(1);
        this.environnement = environnement;
        this.nbreEnnemiApparu = 0;
        this.ennemisASpawn = new ArrayList<>();
        this.niveau = niveau;
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

    public void t() {
        switch (this.getNumeroVague()) {
            case 1:
                this.vague1();
                break;
        }
        if(this.environnement.getEnnemis().isEmpty() && this.ennemisASpawn.isEmpty()) {
            this.setNumeroVague(this.getNumeroVague()+1);
        }
        if(this.environnement.getNbTours() % TEMPS_ATTENTE == 0) {
            System.out.println("teee");
            for(int i = 0;i<this.ennemisASpawn.size();i++) {
                this.environnement.getEnnemis().add(this.ennemisASpawn.remove(i));
            }
        }

    }

    public void vague1() {
        if(this.nbreEnnemiApparu < 3) {
            Niveau terrain = new Niveau(this.niveau);
            for (int i = 0; i < terrain.getBases().size(); i++) {
                System.out.println("te");
                Point base = terrain.getBases().get(i);
                Point entree = terrain.getEntrees().get(i);
                BFS bfs = new BFS(this.environnement.getTerrainDeJeu(), base);
                ArrayList<Point> chemin = bfs.cheminDepuisSource(entree);

                for (int j = 0; j < 3; j++) {
                    this.ennemisASpawn.add(new Bogue(this.environnement, chemin));
                    this.nbreEnnemiApparu++;
                }
            }
        }
    }
    

}

 */
