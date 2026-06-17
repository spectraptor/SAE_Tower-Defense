package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCammoufle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileFragmentation;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

/**
 * La classe Compilateur constitue le bâtiment de base du jeu
 * Il n'est pas capable d'attaquer les ennemisc camouflés et volants.
 */
public class Compilateur extends BatimentTir {
    public Compilateur(double x, double y, Environnement env) {
        super("Compilateur",x, y, 100,1, 100,200, env);
    }


    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        Projectile p =  new MissileTete(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
        switch(this.getNiveau()) {
            case 2:
                p.setVitesse((int) (p.getVitesse() * 1.5));
                break;
            case 3:
                p.setVitesse((int) (p.getVitesse() * 1.8));
                break;
            case 4 :
                p.setVitesse((int) (p.getVitesse()*2.5));
                break;
        }
        return p;
    }

    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return (!(cible instanceof EnnemiVolant) && !(cible instanceof EnnemiCammoufle));
    }

}

