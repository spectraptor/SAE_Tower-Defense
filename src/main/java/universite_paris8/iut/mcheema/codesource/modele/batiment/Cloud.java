package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public class Cloud extends BatimentTir {
    public Cloud(String nom, int x, int y, int portee, int degat, int prix, double cTir, Environnement env) {
        super(nom, x, y, portee, degat, prix, cTir, env);
    }

    @Override
    public void effectueAction() {

    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        return null;
    }

}
