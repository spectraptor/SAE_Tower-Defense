package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCammoufle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
import universite_paris8.iut.mcheema.codesource.modele.projectile.*;

public class BombeLogique extends BatimentTir {
    public BombeLogique(double x, double y, Environnement env) {
        super("Bombe Logique", x, y, 100,5, 500,100, env);
    }


    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return (!(cible instanceof EnnemiVolant) && !(cible instanceof EnnemiCammoufle));
    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        Projectile p;
        switch(this.getNiveau()) {
            case 1,2,3:
                p =  new MissileZone(this.getX(),this.getY(),this.getDegat(),cible.getX(),cible.getY(),this.getEnvironnement());
                break;
            default:
                 p = new MissileFragmentation(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
                 p.setVitesse(p.getVitesse()*2);
                 break;
        }
        return p;
    }

}
