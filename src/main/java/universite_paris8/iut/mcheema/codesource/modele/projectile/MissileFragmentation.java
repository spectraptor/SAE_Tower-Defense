package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public class MissileFragmentation extends MissileTeleguide {
    // Va a ces coord et va ensuite se fragmenté en plusieurs missiles a tete chercheuse
    private int XSUIVRE = 320;
    private int YSUIVRE = 6;
    private int DISTANCE_MISSILE = 8;
    public MissileFragmentation(double x, double y,int degat, Ennemi cible, Environnement env) {
        super(x, y,degat, 2,cible, env);
    }

    @Override
    public void effectueAction() {
        double distX =  XSUIVRE - this.getX();
        double distY =  YSUIVRE - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);
        super.deplaceMissile(distX,distY,distance);
        if (distance <= this.getVitesse()) {
            this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX()+DISTANCE_MISSILE,this.getY() ,this.getDegat(),this.getCible(),this.getEnvironnement()));
            this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX()-DISTANCE_MISSILE,this.getY() ,this.getDegat(),this.getCible(),this.getEnvironnement()));
            this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX(),this.getY() ,this.getDegat(),this.getCible(),this.getEnvironnement()));
            this.setEstArrive(true);
        }
    }

}
