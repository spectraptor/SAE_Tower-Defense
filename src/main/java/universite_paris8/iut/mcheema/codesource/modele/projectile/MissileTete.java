package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.Objects;

public class MissileTete extends Projectile {
    private Ennemi cible;
    public MissileTete(double x, double y, Ennemi cible, Environnement env) {
        super(x, y, 1, 1,env);
        this.cible = cible;
    }

    @Override
    public void effectueAction() {
        double distX = this.cible.getX() - this.getX();
        double distY = this.cible.getY() - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);

        if(distance <= this.getVitesse()) {
            this.cible.subirDegats(this.getDegat());
            this.setEstArrive(true);
        }
        else {
            this.deplaceMissile(distX,distY,distance);
        }

        if(!this.cible.estVivant()) {
            if(this.getX() == this.cible.getX() && this.getY() == this.cible.getY()) {
                this.setEstArrive(true);
            }
        }
    }

}
