package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

/**
 * La classe Batiment s'occupe de la gestion de tours et de leur logique.
 * Elle s'occupe de leurs comportements, de leur déplacements, améliorations...
 */

public abstract class Batiment {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private int portee;
    private int cadenceTir;
    private Environnement environnement;

    public Batiment(int x, int y, int portee, int cTir, Environnement env) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.portee = portee;
        this.cadenceTir = cTir;
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

    public void setCadenceTir(int cadenceTir) {
        this.cadenceTir = cadenceTir;
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }

    public int getCadenceTir() {
        return this.cadenceTir;
    }

    public int getPortee() {
        return this.portee;
    }

    public abstract Ennemi ennemiDansPortee();

    public abstract void effectueAction();

    /**
     * Calcul la distance entre un batiment et un ennemi
     * @param ennemi l'ennemi dont on suhaite connaitre la distance par rapport au batiment
     * @return la distance euclidienne entre le batiment et l'ennemi
     */
    public double calculDistance(Ennemi ennemi) {
        return  (Math.sqrt((this.getX() - ennemi.getX()) * (this.getX() - ennemi.getX()) + (this.getY() - ennemi.getY()) * (this.getY() - ennemi.getY())));
    }

    public String toString() {
        return "Position du bâtiment : " + this.getX() + ";" + this.getY();
    }
}
