package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCammoufle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
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
    public void effectueAction() {
        Ennemi ennemi = this.ennemiDansPortee();
        if (ennemi != null && !(ennemi instanceof EnnemiCammoufle) && !(ennemi instanceof EnnemiVolant)) {
            if (this.estCapableDeTirer()) {
                this.getEnvironnement().ajouterProjectile(new MissileTete(this.getX(), this.getY(),this.getDegat(), ennemi, this.getEnvironnement()));
            }

        }
    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        return null;
    }

}

