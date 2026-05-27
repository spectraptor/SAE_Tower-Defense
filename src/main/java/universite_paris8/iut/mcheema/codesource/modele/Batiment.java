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
    private int degat;
    private int vitesseAttaque;
    private Environnement environnement;

    public Batiment(int x, int y, int portee, int degat, int vAttaque, Environnement env) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.portee = portee;
        this.degat = degat;
        this.vitesseAttaque = vAttaque;
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

    public int getDegat() {
        return this.degat;
    }

    public void setVitesseAttaque(int vitesseAttaque) {
        this.vitesseAttaque = vitesseAttaque;
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }

    public int getVitesseAttaque() {
        return this.vitesseAttaque;
    }

    public int getPortee() {
        return this.portee;
    }

    public abstract void effectueAction(Ennemi ennemi);

    public String toString() {
        return "Position du bâtiment : " + this.getX() + ";" + this.getY();
    }
}
