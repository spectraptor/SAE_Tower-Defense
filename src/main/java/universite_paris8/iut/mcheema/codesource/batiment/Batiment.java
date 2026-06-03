package universite_paris8.iut.mcheema.codesource.batiment;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

/**
 * La classe Batiment s'occupe de la gestion de tours et de leur logique.
 * Elle s'occupe de leurs comportements, de leur déplacements, améliorations...
 */

public abstract class Batiment {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private int portee;
    private Environnement environnement;

    public Batiment(int x, int y, int portee, Environnement env) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.portee = portee;
        this.environnement = env;
    }

    public final IntegerProperty xProperty() {
        return this.xProperty;
    }

    public final IntegerProperty yProperty() {
        return this.yProperty;
    }

    public final int getX() {
        return this.xProperty.getValue();
    }

    public final int getY() {
        return this.yProperty.getValue();
    }

    public final void setX(int x) {
        this.xProperty.set(x);
    }

    public final void setY(int y) {
        this.yProperty.set(y);
    }


    public Environnement getEnvironnement() {
        return this.environnement;
    }

    public int getPortee() {
        return this.portee;
    }

    public abstract void effectueAction();

    /**
     * Calcul la distance entre un batiment et un ennemi
     * @param ennemi l'ennemi dont on souhaite connaitre la distance par rapport au batiment
     * @return la distance euclidienne entre le batiment et l'ennemi
     */
    public double calculDistance(Ennemi ennemi) {
        return  (Math.sqrt((this.getX() - ennemi.getX()) * (this.getX() - ennemi.getX()) + (this.getY() - ennemi.getY()) * (this.getY() - ennemi.getY())));
    }

    public double calculDistance(Batiment batiment) {
        return  (Math.sqrt((this.getX() - batiment.getX()) * (this.getX() - batiment.getX()) + (this.getY() - batiment.getY()) * (this.getY() - batiment.getY())));
    }



    /**
     * Recherche l'ennemi le plus proche de la tour parmi
     * tous les ennemis situés dans sa porté.
     * @return l'ennemi le plus proche dans la portée de la tour ou null si
     * aucun ennemi n'est dans sa portéee
     */
    public Ennemi ennemiDansPortee() {
        Ennemi ennemiRetourne = null;
        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
            if (this.calculDistance(ennemi) <= this.getPortee()) {
                if (!ennemi.estCamoufle()) {
                    if (ennemiRetourne == null) {
                        ennemiRetourne = ennemi;
                    }
                    else {
                        double distActu = calculDistance(ennemiRetourne);
                        double distNouv = calculDistance(ennemi);
                        if (distNouv < distActu) {
                            ennemiRetourne = ennemi;
                        }
                    }
                }
            }
        }
        return ennemiRetourne;
    }


    public String toString() {
        return "Position du bâtiment : " + this.getX() + ";" + this.getY();
    }

}
