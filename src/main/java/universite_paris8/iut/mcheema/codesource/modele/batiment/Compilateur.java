package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;

public class Compilateur extends BatimentTir {
    public Compilateur(int x, int y, Environnement env) {
        super("Compilateur",x, y, 100,1, 200, env);
    }

    @Override
    public void effectueAction() {
        Ennemi ennemi = this.ennemiDansPortee();
        if (ennemi != null && !ennemi.estCamoufle()) {
            if (this.getEnvironnement().getNbTours() % this.getCadenceTir() == 0) {
                this.getEnvironnement().ajouterProjectile(new MissileTete(this.getX(), this.getY(),this.getDegat(), ennemi, this.getEnvironnement()));
            }

        }
    }

}

