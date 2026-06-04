package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.util.ArrayList;

/**
 * Ennemi sont les ennemis qui se déplacent vers la base, il connait son chemin, les pv sa vitesse et peut se déplacer ou/et faire une action.
 */

public abstract class Ennemi {
    private String id;
    private static int idCpt = 0;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private int pv;
    private int vitesse;
    private int argentDonne;
    private Environnement environnement;
    private ArrayList<Point> chemin;
    private int indiceChemin;

    public Ennemi(int pv, int vitesse, int argentDonne, Environnement env, ArrayList<Point> chemin) {
        idCpt++;
        this.id = "E" + idCpt;
        this.xProperty = new SimpleIntegerProperty(0);
        this.yProperty = new SimpleIntegerProperty(0);
        this.pv = pv;
        this.vitesse = vitesse;
        this.argentDonne = argentDonne;
        this.environnement = env;
        this.indiceChemin = 0;
        setChemin(chemin);
    }

    public Ennemi(int x, int y, int pv, int vitesse, int argentDonne, Environnement env, ArrayList<Point> chemin) {
        idCpt++;
        this.id = "E" + idCpt;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.pv = pv;
        this.vitesse = vitesse;
        this.argentDonne = argentDonne;
        this.environnement = env;
        this.indiceChemin = 0;
        setChemin(chemin);
    }


    public String getId() {
        return this.id;
    }

    public final int getX() {
        return this.xProperty.getValue();
    }

    public final void setX(int x) {
        this.xProperty.setValue(x);
    }

    public final IntegerProperty xProperty() {
        return this.xProperty;
    }

    public final int getY() {
        return this.yProperty.getValue();
    }

    public final void setY(int y) {
        this.yProperty.setValue(y);
    }

    public final IntegerProperty yProperty() {
        return this.yProperty;
    }

    public int getPv() {
        return this.pv;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public boolean estVivant() {
        return this.pv>0;
    }

    public void meurt() {
        this.environnement.ajouterArgent(argentDonne);
        this.pv = 0;
    }

    public boolean estCamoufle() {
        return false;
    }


    public void subirDegats(int degat) {
        if (this.pv - degat < 0)
            this.meurt();
        else
            this.pv -= degat;
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }

    public void seDeplace() {
            Point prochaine = this.chemin.get(this.indiceChemin + 1);
            int cibleX = prochaine.getColonne() * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE/2;
            int cibleY = prochaine.getLigne() * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE/2;

            int diffX = cibleX - this.getX();
            int diffY = cibleY - this.getY();
            double distance = Math.sqrt(diffX * diffX + diffY * diffY);

            if (distance > this.vitesse) {
                this.setX(this.getX() + (int) (diffX / distance * this.vitesse));
                this.setY(this.getY() + (int) (diffY / distance * this.vitesse));
            } else {
                this.setX(cibleX);
                this.setY(cibleY);
                this.indiceChemin++;
            }
    }

    public void setChemin(ArrayList<Point> chemin) {
        this.chemin = chemin;
        if (chemin != null && !chemin.isEmpty()) {
            Point depart = chemin.get(0);
            this.setX(depart.getColonne() * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE/2);
            this.setY(depart.getLigne() * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE/2);
        }
    }

    public boolean aAtteintDestination() {
        return this.chemin == null || this.indiceChemin >= this.chemin.size() - 1;
    }

    /**
     * Cette méthode va effectuer l'action de base d'un ennemi qui est de se déplacer
     * et de vérifier s'il peut attaquer la base.
     * Elle sera réécrite pour chaque ennemi possédant des actions supplémentaires.
     */
    public void effectueAction() {
        if (!aAtteintDestination())
            this.seDeplace();
        else {
            this.getEnvironnement().getBase().subirDegats(this.getPv());
            this.meurt();
        }
    }

    public ArrayList<Point> getChemin() {
        return this.chemin;
    }

    public int getIndCheminSuiv() {
        return this.indiceChemin+1;
    }

    public String toString() {
        return "ID de l'ennemi : " + this.id +
                "\nPV de l'ennemi : " + this.pv +
                "\nPosition de l'ennemi : " + this.getX() + ";" + this.getY();
    }

}
