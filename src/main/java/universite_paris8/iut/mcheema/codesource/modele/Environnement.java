package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

import static universite_paris8.iut.mcheema.codesource.modele.Terrain.TAILLE_TUILLE;

public class Environnement {
    private int largeur; //TODO supprimer car c'est dans Terrain
    private int hauteur;
    private ArrayList<Ennemi> ennemis;
    private int nbreVague;
    private Terrain terrainDeJeu;


    public Environnement(int niveau, int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.ennemis = new ArrayList<>();
        this.nbreVague = 0;
        this.terrainDeJeu = new Terrain(niveau);
    }

    public Terrain getTerrainDeJeu() {
        return this.terrainDeJeu;
    }

    public ArrayList<Ennemi> getEnnemis() {
        return this.ennemis;
    }

    public boolean estDedans(int x,int y) {
        //return x>=0 && x < this.largeur && y >= 0 && y < this.hauteur;
        return this.terrainDeJeu.estDansTerrain(x,y);
    } // TODO déplacée dans Terrain

    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }


    public boolean tuileEstAccessibleCoords(int nouveauX, int nouveauY) {
        int ligne = nouveauY / TAILLE_TUILLE;
        int colonne = nouveauX / TAILLE_TUILLE;
        return this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 'e' && this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 'h' && this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 't';
    }
}
