package universite_paris8.iut.mcheema.codesource.modele;

/*

 */

public class Bogue extends Ennemi{
    public Bogue(int x, int y, Environnement env) {

        super(x, y, 8, 3, 5, env);
    }

    @Override
    public void effectueAction() {
        this.seDeplace();
        if(Math.abs(this.getX()-this.getEnvironnement().getBase().getX())<=10 && Math.abs(this.getY()-this.getEnvironnement().getBase().getY())<=10) {
            this.getEnvironnement().getBase().subirDegats(this.getPv());
            this.meurt();
        }
    }
}
