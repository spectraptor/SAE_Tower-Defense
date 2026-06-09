package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.Entite;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.util.ArrayList;

/**
 * Ennemi sont les ennemis qui se déplacent vers la base, il connait son chemin, les pv sa vitesse et peut se déplacer ou/et faire une action.
 */

public abstract class Ennemi extends Entite {
    private static int idCpt = 0;
    private int pv;
    private int vitesse;
    private int argentDonne;
    private ArrayList<Point> chemin;
    private int indiceChemin;

    public Ennemi(int pv, int vitesse, int argentDonne, Environnement env, ArrayList<Point> chemin) {
        super("E" + idCpt++, 0, 0, env);
        this.pv = pv;
        this.vitesse = vitesse;
        this.argentDonne = argentDonne;
        this.indiceChemin = 0;
        setChemin(chemin);
    }

    public Ennemi(int pv, double x, double y, int vitesse, int argentDonne, Environnement env, ArrayList<Point> chemin) {
        super("E" + idCpt++, x, y, env);
        this.pv = pv;
        this.vitesse = vitesse;
        this.argentDonne = argentDonne;
        this.indiceChemin = 0;
        setChemin(chemin);
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
        if(this.indiceChemin != this.chemin.size() -1) {
            this.getEnvironnement().ajouterArgent(argentDonne);
        }
        this.pv = 0;
    }


    public void subirDegats(int degat) {
        if (this.pv - degat <= 0)
            this.meurt();
        else
            this.pv -= degat;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void seDeplace() {
            Point prochaine = this.chemin.get(this.indiceChemin + 1);
            int cibleX = prochaine.getColonne() * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE/2;
            int cibleY = prochaine.getLigne() * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE/2;

            double diffX = cibleX - this.getX();
            double diffY = cibleY - this.getY();
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
    @Override
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

    public int getIndiceChemin() {
        return this.indiceChemin;
    }

    public void setIndiceChemin(int indice) {
        this.indiceChemin = indice;
    }

    public String toString() {
        return "ID de l'ennemi : " + this.getId() +
                "\nPV de l'ennemi : " + this.pv +
                "\nPosition de l'ennemi : " + this.getX() + ";" + this.getY();
    }

}
