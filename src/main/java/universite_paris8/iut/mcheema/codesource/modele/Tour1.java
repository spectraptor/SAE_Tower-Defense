package universite_paris8.iut.mcheema.codesource.modele;

public class Tour1 extends Batiment{
    public Tour1(int x, int y, Environnement env) {
        super(x, y, 10, 5, 10, env);
    }

    @Override
    public void effectueAction(Ennemi ennemi) {
        if (this.getEnvironnement().estDansRayonTour(this, ennemi))
            if (this.getEnvironnement().getNbTours() % this.getVitesseAttaque() == 0)
                ennemi.faireDegat(this.getDegat());
    }

}
