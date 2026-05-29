package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class GrosBogue extends Ennemi {
    public GrosBogue(Environnement env) {
        super(4, 3, 4, env);
    }
    public GrosBogue(int x, int y, Environnement env) {
        super(x, y, 4, 3, 4, env);
    }

    @Override
    public void effectueAction() {
        super.effectueAction();
        if(!this.estVivant()) {
            this.getEnvironnement().getEnnemis().add(new Bogue(this.getX(),this.getY(),this.getEnvironnement()));
            this.getEnvironnement().getEnnemis().add(new Bogue(this.getX(),this.getY(),this.getEnvironnement()));
        }
    }
}
