package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCammoufle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileFragmentation;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public class Debugger extends BatimentTir {

    public Debugger(double x, double y, Environnement env) {
        super("Debugger",x, y, 150,5, 50,150, env);
    }


    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        Projectile projectile;
        switch (this.getNiveau()) {
            case 1,2,3:
                projectile = new MissileTete(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
                break;
            default:
                projectile = new MissileFragmentation(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
                break;
        }
        return projectile;
    }
    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return (!(cible instanceof EnnemiVolant));
    }

}
