package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class Ralentisseur extends Ennemi {
    private int ralentissement;

    public Ralentisseur(Environnement env) {
        super(10, 4, 10, env);
        this.ralentissement = 8;
    }


    public Ralentisseur(int x, int y, Environnement env) {
        super(x, y, 10, 4, 10, env);
        this.ralentissement = 8;
    }

    @Override
    public void effectueAction() {
        super.effectueAction();
        for(Batiment batiment : this.getEnvironnement().getBatiments()) {
            if (Math.abs(this.getX() - batiment.getX()) <= 10 && Math.abs(this.getY() - batiment.getY()) <= 10) {
                batiment.setCadenceTir(this.ralentissement);
            }
        }
    }
}
