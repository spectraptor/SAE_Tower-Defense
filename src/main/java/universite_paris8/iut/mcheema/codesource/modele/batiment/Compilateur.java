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
        super("Compilateur",x, y, 100,1, 200,200, env);
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
        return (!(cible instanceof EnnemiVolant) && !(cible instanceof EnnemiCammoufle));
    }

}

