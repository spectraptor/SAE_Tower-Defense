package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

import  universite_paris8.iut.mcheema.codesource.modele.*;

/*

 */

public class Environnement {
    private ArrayList<Ennemi> ennemis;
    private int nbreVague;
    private Terrain terrainDeJeu;
    private int nbTours;


    public Environnement(int niveau) {
        this.ennemis = new ArrayList<>();
        this.nbreVague = 0;
        this.terrainDeJeu = new Terrain(niveau);
        this.nbTours = 0;
    }

    public Terrain getTerrainDeJeu() {
        return this.terrainDeJeu;
    }

    public ArrayList<Ennemi> getEnnemis() {
        return this.ennemis;
    }

    public boolean estDansTerrain(int x, int y) {
        return this.terrainDeJeu.estDansTerrain(x,y);
    }

    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }

    public void unTour() {
        for (Ennemi ennemi : this.ennemis) {
            if (this.nbTours % 5 == 0) {
                ennemi.seDeplace();
            }
        }
        this.nbTours++;
    }


    public int getNbTours() {
        return this.nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
}
