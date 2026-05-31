package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public class Tour1 extends Batiment {
    public Tour1(int x, int y, Environnement env) {
        super(x, y, 35, 20, env);
    }

    @Override
    public void effectueAction() {
        Ennemi ennemi = this.ennemiDansPortee();
        if (ennemi != null) {
            if (this.getEnvironnement().getNbTours() % this.getCadenceTir() == 0)
                this.getEnvironnement().ajouterProjectile(new Projectile(this.getX(), this.getY(), 1, 2, ennemi, this.getEnvironnement()));
        }
    }

    /**
     *Recherche l'ennemi non cammouflé le plus proche de la tour parmi
     * tous les ennemis situés dans sa porté.
     * @return l'ennemi le plus proche dans la portée de la tour ou null si
     * aucun ennemi n'est dans sa portéee
     */
    public Ennemi ennemiDansPortee() {
        Ennemi ennemiRetourne = null;
        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
            if (this.calculDistance(ennemi) <= this.getPortee()) {
                if (!ennemi.estCamoufle()) {
                    if (ennemiRetourne == null) {
                        ennemiRetourne = ennemi;
                    }
                    else {
                        double distActu = calculDistance(ennemiRetourne);
                        double distNouv = calculDistance(ennemi);
                        if (distNouv < distActu) {
                            ennemiRetourne = ennemi;
                        }
                    }
                }
            }
        }
        return ennemiRetourne;
    }

}

