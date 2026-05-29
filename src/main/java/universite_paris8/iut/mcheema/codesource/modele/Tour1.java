package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public class Tour1 extends Batiment{
    public Tour1(int x, int y, Environnement env) {
        super(x, y, 30, 1, 10, env);
    }

    @Override
    public void effectueAction(Ennemi ennemi) {
        if(!ennemi.estCamoufle())
            if (this.getEnvironnement().estDansRayonTour(this, ennemi))
             if (this.getEnvironnement().getNbTours() % this.getVitesseAttaque() == 0)
                 ennemi.subirDegats(this.getDegat());
    }

}
