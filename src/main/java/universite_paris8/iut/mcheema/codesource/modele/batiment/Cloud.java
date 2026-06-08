package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public class Cloud extends BatimentTir {
    public Cloud(int x, int y, Environnement env) {
        super("Cloud", x, y, 50, 1, 50, 100, env);
    }

    @Override
    public void effectueAction() {

    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        return null;
    }

}
