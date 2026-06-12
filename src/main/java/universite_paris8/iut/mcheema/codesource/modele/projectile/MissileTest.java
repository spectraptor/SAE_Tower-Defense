package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public class MissileTest extends MissileTeleguide {
    public MissileTest(int x, int y,int degat, Ennemi cible, Environnement env) {
        super(x, y, degat, 4, cible,env);
    }

    @Override
    public void effectueAction() {
        double distX = (this.getCible().getChemin().get(this.getCible().getIndiceChemin()+1).getColonne() * Terrain.TAILLE_TUILLE) + (Terrain.TAILLE_TUILLE / 2) - this.getX();
        double distY = (this.getCible().getChemin().get(this.getCible().getIndiceChemin()+1).getLigne() * Terrain.TAILLE_TUILLE) + (Terrain.TAILLE_TUILLE / 2) - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);

        if(distance <= this.getVitesse()) {
            this.getCible().subirDegats(this.getDegat());
            this.setEstArrive(true);
        }
        else {
            double vUnitX = distX / distance;
            double vUnitY = distY / distance;
            this.setX(this.getX() + vUnitX * this.getVitesse());
            this.setY(this.getY() + vUnitY * this.getVitesse());
        }

    }

}




