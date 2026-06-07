package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileFragmentation;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public class BombeLogique extends BatimentTir {
    public BombeLogique(int x, int y, Environnement env) {
        super("Bombe Logique",x, y, 100,5, 200,100, env);
    }

    @Override
    public void effectueAction() {
        Ennemi ennemi = this.ennemiDansPortee();
        if (ennemi != null) {
            if (this.getEnvironnement().getNbTours() % (int) this.getCadenceTir() == 0) {
                this.getEnvironnement().ajouterProjectile(this.choisirProjectile(ennemi));
            }
        }
    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        Projectile p = null;
        switch(this.getNiveau()) {
            case 1:
                p =  new MissileZone(this.getX(),this.getY(),this.getDegat(),cible.getX(),cible.getY(),this.getEnvironnement());
                break;
            case 2:
                 p = new MissileFragmentation(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
                 p.setVitesse(p.getVitesse()*4);
                 break;
        }
        return p;
    }

}
