package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import  universite_paris8.iut.mcheema.codesource.modele.*;

/*

 */

public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private int nbreVague;
    private Terrain terrainDeJeu;
    private int nbTours;
    private Base base;


    public Environnement(int niveau,Base base) {
        this.ennemis = FXCollections.observableArrayList();
        this.nbreVague = 0;
        this.terrainDeJeu = new Terrain(niveau);
        this.nbTours = 0;
        this.base = base;
    }

    public Terrain getTerrainDeJeu() {
        return this.terrainDeJeu;
    }

    public void setBase() {

    }

    public ObservableList<Ennemi> getEnnemis() {
        return this.ennemis;
    }

    public boolean estDansTerrain(int x, int y) {
        return this.terrainDeJeu.estDansTerrain(x,y);
    }

    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }

    public void unTour() {
        ArrayList<Ennemi> ennemisMort = new ArrayList<>();
        for (Ennemi ennemi : this.ennemis) {
            if (this.nbTours % 5 == 0) {

                int nPosX = ennemi.getX() + (ennemi.getVitesse() * ennemi.getDx());
                int nPosY = ennemi.getY() + (ennemi.getVitesse() * ennemi.getDy());

                if (!tuileEstAccessible(nPosX, nPosY)) {
                    ennemi.attribuerDirectionAleatoire();
                }
                else {
                    if(ennemi.estVivant()) {
                        ennemi.effectueAction();
                    }
                    else {
                        ennemisMort.add(ennemi);
                    }
                }
            }
        }
        for(Ennemi ennemi : ennemisMort) {
            ennemi.meurt();
            this.getEnnemis().remove(ennemi);
        }
        this.nbTours++;
    }

    public Base getBase() {
        return this.base;
    }


    public boolean tuileEstAccessible(int nouveauX, int nouveauY) {
        return this.terrainDeJeu.tuileEstAccessible(nouveauX,nouveauY);
    }

    public int getNbTours() {
        return this.nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
}
