package universite_paris8.iut.mcheema.codesource.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private ArrayList<Batiment> batiments;
    private int nbreVague;
    private Terrain terrainDeJeu;
    private int nbTours;
    private Base base;


    public Environnement(int niveau, Base base) {
        this.ennemis = FXCollections.observableArrayList();
        this.batiments = new ArrayList<>();
        this.nbreVague = 0;
        this.terrainDeJeu = new Terrain(niveau);
        this.nbTours = 0;
        this.base = base;
    }

    public Terrain getTerrainDeJeu() {
        return this.terrainDeJeu;
    }

    public ObservableList<Ennemi> getEnnemis() {
        return this.ennemis;
    }

    public ArrayList<Batiment> getBatiments() {
        return this.batiments;
    }

    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }


    public void ajouterBatiment(Batiment batiment) {
        this.batiments.add(batiment);
    }

    public Base getBase() {
        return this.base;
    }

    public void unTour() {
        ArrayList<Ennemi> ennemisMort = new ArrayList<>();
        if (!this.partieEstFinie()) {
            for (Ennemi ennemi : this.ennemis) {
                if (this.nbTours % 5 == 0) {
                    if (ennemi.estVivant()) {
                        ennemi.effectueAction();
                    } else {
                        ennemisMort.add(ennemi);
                    }
                }
            }
            for (Batiment batiment : this.batiments) {
                batiment.effectueAction();
            }

            for (Ennemi ennemi : ennemisMort) {
                ennemi.meurt();
                this.getEnnemis().remove(ennemi);
            }
            this.nbTours++;
        }
    }

    /**
     * Regarde si un ennemi se trouve dans le rayon d'un batiment
     * @param batiment le batiment qui sert de défense
     * @param ennemi l'ennemi qui peut être attaqué
     * @return vrai si l'ennemi se trouve dans le rayon, faux sinon
     */
    /*
    public boolean estDansRayonTour(Batiment batiment, Ennemi ennemi) {
        double distance = Math.sqrt((batiment.getX()- ennemi.getX()) * (batiment.getX()- ennemi.getX()) + (batiment.getY()- ennemi.getY()) * (batiment.getY()- ennemi.getY()));
        return distance <= batiment.getPortee();
    }
     */


    public boolean tuileEstAccessibleCoords(int nouveauX, int nouveauY) {
        return this.terrainDeJeu.tuileEstAccessibleCoords(nouveauX,nouveauY);
    }

    /**
     * Regarde si une tour est à proximité de coordonnées du clic de la souris.
     * @param x l'abscisse du clic de la souris
     * @param y l'ordonnée du clic de la souris.
     * @return vrai si la distance entre le click et une tour est < à rayonDistanceAutorisee, faux autrement.
     */
    public boolean estAdjacentATour(int x, int y) {
        boolean sortieBoucle = false;
        int i = 0;
        while (i < this.batiments.size() && !sortieBoucle) {
            if(x == this.batiments.get(i).getX() && y == this.batiments.get(i).getY()) {
                sortieBoucle = true;
            }
            i++;
        }

        return sortieBoucle;
    }

    public boolean partieEstFinie() {
        return this.getEnnemis().isEmpty() || this.getBase().estDetruite();
    }

    public int getNbTours() {
        return this.nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
}

