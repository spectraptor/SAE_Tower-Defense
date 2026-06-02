package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public class MissileFragmentation extends Projectile {
    private int xCible;
    private int yCible;
    private Ennemi cible;
    public MissileFragmentation(double x, double y, Environnement env, int xCible, int yCible , Ennemi cible) {
        super(x, y,2, 4, env);
        this.xCible = xCible;
        this.yCible = yCible;
        this.cible = cible;
    }

    @Override
    public void effectueAction() {
        double distX = (double) this.xCible - 50 - this.getX();
        double distY = (double) this.yCible  - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);
        super.deplaceMissile(distX,distY,distance);
        if (distance <= this.getVitesse()) {
            this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX() + 5,this.getY() + 4,this.getEnvironnement(),this.cible));
            this.setEstArrive(true);
        }
    }
}
