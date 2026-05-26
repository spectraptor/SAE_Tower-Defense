package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import  universite_paris8.iut.mcheema.codesource.modele.*;

/*

 */

public class Environnement {

    private ArrayList<Batiment> batiments;
    private ObservableList<Ennemi> ennemis;
    private int nbreVague;
    private Terrain terrainDeJeu;
    private int nbTours;
    private Base base;

    public Environnement(int niveau) {
        this.batiments = new ArrayList<>();
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

    public void ajouterBatiment(Batiment batiment) {
        this.batiments.add(batiment);
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
            for (Batiment batiment : this.batiments) {
                batiment.effectueAction(ennemi);
            }
            System.out.println(ennemi);
        }
        for(Ennemi ennemi : ennemisMort) {
            ennemi.meurt();
            this.getEnnemis().remove(ennemi);
        }
        this.nbTours++;
    }

    /**
     * Regarde si un ennemi se trouve dans le rayon d'un batiment
     * @param batiment le batiment qui sert de défense
     * @param ennemi l'ennemi qui peut être attaqué
     * @return vrai si l'ennemi se trouve dans le rayon, faux sinon
     */
    public boolean estDansRayonTour(Batiment batiment, Ennemi ennemi) {
        int distanceHorz = Math.abs(batiment.getX() - ennemi.getX());
        int distanceVert = Math.abs(batiment.getY() - ennemi.getY());
        return (distanceHorz <= batiment.getPortee()) && (distanceVert <= batiment.getPortee());

    public Base getBase() {
        return this.base;
    }


    public boolean tuileEstAccessible(int nouveauX, int nouveauY) {
        return this.terrainDeJeu.tuileEstAccessible(nouveauX,nouveauY);
    }

    /**
     * Regarde si une tour est à proximité de coordonnées du clic de la souris.
     * @param x l'abscisse du clic de la souris
     * @param y l'ordonnée du clic de la souris.
     * @return vrai si la distance entre le click et une tour est < à rayonDistanceAutorisee, faux autrement.
     */
    public boolean estAdjacentATour(int x, int y) {
        final int rayonDistanceAutorisee = 30; // distance minimale entre les batiments (pixels)
        int distanceX, distanceY;
        for (Batiment bat : batiments) {
            distanceX = Math.abs(bat.getX() - x);
            distanceY = Math.abs(bat.getY() - y);
            /* Le "&&" est préférable au "||" -> si on fait juste un ou,
            cela voudrait dire que même si on peut placer un bâtiment horizontalement, puisque verticalement on ne peut pas,
            la tour est implaçable.
            */
            if (distanceX < rayonDistanceAutorisee && distanceY < rayonDistanceAutorisee)
                return true;
        }

        return false;
    }

    public int getNbTours() {
        return this.nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
}
