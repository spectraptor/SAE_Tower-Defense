package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public class MissileZone extends Projectile {
    private int portee;
    private int xCible;
    private int yCible;
    public MissileZone(double x, double y, int xCible, int yCible, Environnement env) {
        super(x, y, 5, 1,env);
        this.portee = 20;
        this.xCible = xCible;
        this.yCible = yCible;
    }

    @Override
    public void effectueAction() {
        double distX = xCible - this.getX();
        double distY = yCible - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);
        if(distance <= this.getVitesse()) {
            this.effectueExplosion();
            this.setEstArrive(true);
        }
        else {
            this.deplaceMissile(distX,distY,distance);
        }
    }

    public void effectueExplosion() {
        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
            if(((ennemi.getX() - this.getX()) * (ennemi.getX() - this.getX())) + ((ennemi.getY() - this.getY()) * ennemi.getY() - this.getY()) <= this.portee * this.portee) {
                ennemi.subirDegats(this.getDegat());
                if(!ennemi.estVivant()) {
                    System.out.println(ennemi);
                }
            }
        }
    }

    public int getPortee() {
        return this.portee;
    }
}
