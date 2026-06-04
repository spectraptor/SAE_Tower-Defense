package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;

public class Debugger extends BatimentTir {

    public Debugger(int x, int y, Environnement env) {
        super("Debugger",x, y, 150,5, 150, env);
    }

    @Override
    public void effectueAction() {
        Ennemi ennemi = this.ennemiDansPortee();

        if (ennemi != null) {
            if (this.getEnvironnement().getNbTours() % this.getCadenceTir() == 0) {
                this.getEnvironnement().ajouterProjectile(new MissileZone(this.getX(), this.getY(),this.getDegat(),
                        ennemi.getX(), ennemi.getY(), this.getEnvironnement()));
            }
        }
    }
}
