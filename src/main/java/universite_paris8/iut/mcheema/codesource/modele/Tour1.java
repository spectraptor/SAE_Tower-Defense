package universite_paris8.iut.mcheema.codesource.modele;

public class Tour1 extends Batiment{
    public Tour1(int x, int y, Environnement env) {
        super(x, y, 10, 5, 3, 20,env);
    }

    @Override
    public void effectueAction(Ennemi e) {
        if(Math.abs(this.getX()-e.getX()) <= 50 && Math.abs(this.getY()-e.getY()) <= 50) {
            if(this.getEnvironnement().getNbTours() % this.getVitesseAttaque() == 0) {
                e.setPv(e.getPv() - this.getDegat());
            }
        }
    }
}
