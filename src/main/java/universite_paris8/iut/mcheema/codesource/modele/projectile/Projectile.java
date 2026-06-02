package universite_paris8.iut.mcheema.codesource.modele.projectile;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.Objects;

public abstract class Projectile {
    private String id;
    private static int idCpt = 0;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private int degat;
    private double vitesse;
    private boolean estArrive;
    private Environnement environnement;

    public Projectile(double x,double y,int degat,double vitesse,Environnement env) {
        idCpt++;
        this.id = "P" + idCpt;
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.degat = degat;
        this.vitesse = vitesse;
        this.estArrive = false;
        this.environnement = env;
    }

    public String getId() {
        return this.id;
    }

    public final double getX() {
        return this.xProperty.getValue();
    }

    public final void setX(double x) {
        this.xProperty.setValue(x);
    }

    public final DoubleProperty xProperty() {
        return this.xProperty;
    }

    public final double getY() {
        return this.yProperty.getValue();
    }

    public final void setY(double y) {
        this.yProperty.setValue(y);
    }

    public final DoubleProperty yProperty() {
        return this.yProperty;
    }

    public abstract void seDeplacer();

    public double getVitesse() {
        return this.vitesse;
    }

    public int getDegat() {
        return this.degat;
    }

    public boolean getEstArrive() {
        return this.estArrive;
    }

    public void setEstArrive(boolean estArrive) {
        this.estArrive = estArrive;
    }

    public Environnement getEnvrionneemnt() {
        return this.environnement;
    }

}