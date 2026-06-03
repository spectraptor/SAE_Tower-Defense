package universite_paris8.iut.mcheema.codesource.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;

public class BombeLogique extends BatimentTir {
    public BombeLogique(int x, int y, Environnement env) {
        super(x, y, 100, 100, env);
    }

    @Override
    public void effectueAction() {
        Ennemi ennemi = this.ennemiDansPortee();
        if (ennemi != null) {
            if (this.getEnvironnement().getNbTours() % this.getCadenceTir() == 0) {
                this.getEnvironnement().ajouterProjectile(new MissileZone(this.getX(), this.getY(), ennemi.getX(),ennemi.getY(), this.getEnvironnement()));
            }
        }
    }

}
