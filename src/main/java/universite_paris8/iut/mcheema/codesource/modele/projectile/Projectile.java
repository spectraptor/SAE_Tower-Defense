package universite_paris8.iut.mcheema.codesource.modele.projectile;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.mcheema.codesource.modele.Entite;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.Objects;

public abstract class Projectile extends Entite {
    private static int idCpt = 0;
    private int degat;
    private int vitesse;
    private boolean estArrive;

    public Projectile(double x, double y,int degat,int vitesse, Environnement env) {
        super("P" + idCpt++, x, y, env);
        this.degat = degat;
        this.vitesse = vitesse;
        this.estArrive = false;
    }


    public abstract void effectueAction();

    public int getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
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

    public void deplaceMissile(double distX, double distY, double distance) {
        double vUnitX = distX / distance;
        double vUnitY = distY / distance;
        this.setX(this.getX() + vUnitX * this.getVitesse());
        this.setY(this.getY() + vUnitY * this.getVitesse());
    }


}