package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCammoufle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileFragmentation;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public class Cloud extends BatimentTir {
    public Cloud(double x, double y, Environnement env) {
        super("Cloud", x, y, 50, 1, 150, 100, env);
    }


    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return (!(cible instanceof EnnemiCammoufle));
    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        Projectile p =  new MissileTete(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
        switch(this.getNiveau()) {
            case 2:
                p.setVitesse((int) (p.getVitesse() * 1.2));
                break;
            case 3:
                p.setVitesse((int) (p.getVitesse() * 1.5));
                break;
            case 4 :
                p.setVitesse(p.getVitesse()*2);
                break;
        }
        return p;
    }

}
