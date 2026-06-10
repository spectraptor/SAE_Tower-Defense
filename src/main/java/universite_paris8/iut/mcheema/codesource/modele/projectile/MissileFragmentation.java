package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public class MissileFragmentation extends MissileTeleguide {
    // Va a ces coord et va ensuite se fragmenté en plusieurs missiles a tete chercheuse
    private final static int X_SUIVRE = 320;
    private final static int Y_SUIVRE = 6;
    private final static int DISTANCE_MISSILE = 8;
    public MissileFragmentation(double x, double y,int degat, Ennemi cible, Environnement env) {
        super(x, y,degat, 2,cible, env);
    }

    @Override
    public void effectueAction() {
        double distX = X_SUIVRE - this.getX();
        double distY = Y_SUIVRE - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);
        super.deplaceMissile(distX, distY, distance);
        int nombreEnnemi = this.getEnvironnement().getEnnemis().size();
        if (distance <= this.getVitesse()) {
            this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX(), this.getY(), this.getDegat(), this.getCible(), this.getEnvironnement()));

           if(nombreEnnemi == 1) {
               this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX() + DISTANCE_MISSILE, this.getY(), this.getDegat(), this.getCible(), this.getEnvironnement()));
               this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX() - DISTANCE_MISSILE, this.getY(), this.getDegat(), this.getCible(), this.getEnvironnement()));
           }
           else if (nombreEnnemi >= 2) {
                Ennemi cible2 = null;
                for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
                    if (ennemi != this.getCible()) {
                        cible2 = ennemi;
                    }
                }
                if (cible2 != null) {
                    if (nombreEnnemi == 2) {
                        this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX() + DISTANCE_MISSILE, this.getY(), this.getDegat(), cible2, this.getEnvironnement()));
                    }
                    if (nombreEnnemi >= 3) {
                        Ennemi cible3 = null;
                        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
                            if (ennemi != this.getCible() && cible2 != ennemi) {
                                cible3 = ennemi;
                            }
                        }
                        if (cible3 != null) {
                            this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX() - DISTANCE_MISSILE, this.getY(), this.getDegat(), cible3, this.getEnvironnement()));
                        }
                    }
                }
            }
            this.setEstArrive(true);
        }
    }

}
