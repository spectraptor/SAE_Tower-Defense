package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.Objects;

public class MissileTete extends MissileTeleguide {
    public MissileTete(double x, double y, Ennemi cible,Environnement env) {
        super(x, y, 1, 1,cible,env);
    }

    @Override
    public void effectueAction() {
        double distX = this.getCible().getX() - this.getX();
        double distY = this.getCible().getY() - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);

        if(distance <= this.getVitesse()) {
            this.getCible().subirDegats(this.getDegat());
            this.setEstArrive(true);
        }
        else {
            this.deplaceMissile(distX,distY,distance);
        }

        if(!this.getCible().estVivant()) {
            if(this.getX() == this.getCible().getX() && this.getY() == this.getCible().getY()) {
                this.setEstArrive(true);
            }
        }
    }

}
