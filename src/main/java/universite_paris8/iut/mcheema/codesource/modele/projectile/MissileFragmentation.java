package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class MissileFragmentation extends Projectile {
    public MissileFragmentation(double x, double y, Environnement env) {
        super(x, y,2, 4, env);
    }

    @Override
    public void seDeplacer() {

    }
}
