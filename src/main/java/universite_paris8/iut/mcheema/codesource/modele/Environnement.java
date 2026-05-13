package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

public class Environnement {
    private int width;
    private int height;
    private ArrayList<Ennemi> ennemis;
    private int nbreVague;
    private Terrain terrainDeJeu;


    public Environnement(int width,int height) {
        this.width = width;
        this.height = height;
        this.ennemis = new ArrayList<>();
        this.nbreVague = 0;
        this.terrainDeJeu = new Terrain();
    }

    public Terrain getTerrainDeJeu() {
        return this.terrainDeJeu;
    }



    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }



}
