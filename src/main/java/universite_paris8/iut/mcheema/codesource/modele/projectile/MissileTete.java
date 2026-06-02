package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.Objects;

public class MissileTete extends Projectile {
    private Ennemi cible;
    public MissileTete(double x, double y, Ennemi cible, Environnement env) {
        super(x, y, 1, 2,env);
        this.cible = cible;
    }

    @Override
    public void seDeplacer() {
        if (this.cible.estVivant()) {
            double distX = this.cible.getX() - this.getX();
            double distY = this.cible.getY() - this.getY();
            double distance = Math.sqrt(distX * distX + distY * distY);

            if(distance <= this.getVitesse()) {
                this.cible.subirDegats(this.getDegat());
                this.setEstArrive(true);
            }
            else {
                double vUnitX = distX / distance;
                double vUnitY = distY / distance;

                this.setX(this.getX() + vUnitX * this.getVitesse());
                this.setY(this.getY() + vUnitY * this.getVitesse());
                if(Objects.equals(this.getId(), "P1")) {
                    //System.out.println("ID : " + this.getId() + "\nDistX : " + distX + "\nDistY : " + distY + "\ndistance : " + distance + "\nvUnitX : " + vUnitX + "\nvUnitY" + vUnitY);
                    //System.out.println(this.getX()+";"+this.getY());
                }
            }
        }
        else {
            this.setEstArrive(true);
        }
    }

}
