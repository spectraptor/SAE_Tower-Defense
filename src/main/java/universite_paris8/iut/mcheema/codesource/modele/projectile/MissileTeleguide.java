package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public abstract class MissileTeleguide extends Projectile {
    private Ennemi cible;

    public MissileTeleguide(double x, double y, int degat, int vitesse, Environnement env,Ennemi cible) {
        super(x, y, degat, vitesse, env);
        this.cible = cible;
    }

    public Ennemi getCible() {
        return this.cible;
    }

    @Override
    public void deplaceMissile(double distX, double distY, double distance) {
        super.deplaceMissile(distX,distY,distance);
    }

}
