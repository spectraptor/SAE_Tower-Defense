package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.Objects;

public class MissileZone extends Projectile {
    private int portee;
    private int xCible;
    private int yCible;
    public MissileZone(double x, double y, int xCible, int yCible, Environnement env) {
        super(x, y, 4, 4,env);
        this.portee = 10;
        this.xCible = xCible;
        this.yCible = yCible;
    }

    @Override
    public void seDeplacer() {
        double distX = xCible - this.getX();
        double distY = yCible - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);
        if(distance <= this.getVitesse()) {
            this.effectueExplosion();
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
                //System.out.println(this.xCible+";"+this.yCible);
            }
        }
    }

    public void effectueExplosion() {
        for (Ennemi ennemi : this.getEnvrionneemnt().getEnnemis()) {
            if(((ennemi.getX() - this.getX()) * (ennemi.getX() - this.getX())) + ((ennemi.getY() - this.getY()) * ennemi.getY() - this.getY()) <= this.portee * this.portee) {
                ennemi.subirDegats(this.getDegat());
                if(!ennemi.estVivant()) {
                    System.out.println(ennemi);
                }
            }
        }
    }
}
