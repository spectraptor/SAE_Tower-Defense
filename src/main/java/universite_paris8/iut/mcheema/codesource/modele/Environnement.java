package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

public class Environnement {
    private int width;
    private int height;
    private ArrayList<Ennemi> ennemis;
    private int nbreVague;

    public Environnement(int width,int height) {
        this.width = width;
        this.height = height;
        this.ennemis = new ArrayList<>();
        this.nbreVague = 0;
    }

    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }

    public boolean estDedans(int x,int y) {
        return x>=0 && x<=this.width && y>=0 && y<=this.height;
    }

}
