package universite_paris8.iut.mcheema.codesource.modele.projectile;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.Objects;

public class Projectile {
    private String id;
    private static int idCpt = 0;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private int degat;
    private int vitesse;
    private Ennemi cible;
    private boolean estArrive;

    public Projectile(double x,double y,int degat,int vitesse,Ennemi cible) {
        idCpt++;
        this.id = "P" + idCpt;
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.degat = degat;
        this.vitesse = vitesse;
        this.cible = cible;
        this.estArrive = false;
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


    public void seDeplacer() {
        if (this.cible.estVivant()) {
            double distX = this.cible.getX() - this.getX();
            double distY = this.cible.getY() - this.getY();
            double distance = Math.sqrt(distX * distX + distY * distY);

            if(distance <= this.vitesse) {
                this.cible.subirDegats(this.degat);
                this.estArrive = true;
            }
            else {
                double vUnitX = distX / distance;
                double vUnitY = distY / distance;

                this.setX(this.getX() + vUnitX * this.vitesse);
                this.setY(this.getY() + vUnitY * this.vitesse);
                if(Objects.equals(this.id, "P1")) {
                    //System.out.println("ID : " + this.getId() + "\nDistX : " + distX + "\nDistY : " + distY + "\ndistance : " + distance + "\nvUnitX : " + vUnitX + "\nvUnitY" + vUnitY);
                    //System.out.println(this.getX()+";"+this.getY());
                }
            }
        }
        else {
            this.estArrive = true;
        }
    }

    public boolean getEstArrive() {
        return this.estArrive;
    }

}
