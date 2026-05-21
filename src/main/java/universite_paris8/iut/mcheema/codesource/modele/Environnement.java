package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

import static universite_paris8.iut.mcheema.codesource.modele.Terrain.TAILLE_TUILLE;

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
            ennemi.effectueAction();
        }
    }


    public boolean tuileEstAccessibleCoords(int nouveauX, int nouveauY) {
        int ligne = nouveauY / TAILLE_TUILLE;
        int colonne = nouveauX / TAILLE_TUILLE;
        return this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 'e' && this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 'h' && this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 't';
    }

    public int getNbTours() {
        return this.nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
}
