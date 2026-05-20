package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

import static universite_paris8.iut.mcheema.codesource.modele.Terrain.TAILLE_TUILLE;

public class Environnement {
    private int width;
    private int height;
    private ArrayList<Ennemi> ennemis;
    private int nbreVague;
    private Terrain terrainDeJeu;


    public Environnement(int niveau,int width,int height) {
        this.width = width;
        this.height = height;
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
        return x>=0 && x < this.width && y >= 0 && y < this.height;
    }

    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }


    public boolean tuileEstAccessible(Ennemi ennemi) {
        int xNouv = ennemi.getX() + (ennemi.getDx() * ennemi.getVitesse());
        int yNouv = ennemi.getY() + (ennemi.getDy() * ennemi.getVitesse());
        int ligne = yNouv / TAILLE_TUILLE;
        int colonne = xNouv / TAILLE_TUILLE;
        return this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 'e' && this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 'h' && this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 't';
    }


    public boolean tuileEstAccessibleCoords(int nouveauX, int nouveauY) {
        int ligne = nouveauY / TAILLE_TUILLE;
        int colonne = nouveauX / TAILLE_TUILLE;
        return this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 'e' && this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 'h' && this.terrainDeJeu.avoirCodeTuile(ligne,colonne) != 't';
    }
}
