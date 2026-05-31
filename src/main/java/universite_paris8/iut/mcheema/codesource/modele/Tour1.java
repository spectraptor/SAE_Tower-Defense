package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public class Tour1 extends Batiment {
    public Tour1(int x, int y, Environnement env) {
        super(x, y, 35, 1, 20, env);
    }

    @Override
    public void effectueAction() {
        Ennemi ennemi = this.ennemiDansPortee();
        if (ennemi != null) {
            if (this.getEnvironnement().getNbTours() % this.getCadenceTir() == 0)
                ennemi.subirDegats(this.getDegat());
        }
    }

    public Ennemi ennemiDansPortee() {
        Ennemi ennemiRetourne = null;
        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
            if (this.getEnvironnement().estDansRayonTour(this, ennemi)) {
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

