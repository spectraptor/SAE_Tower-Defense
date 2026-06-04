package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentTir;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;

import java.util.ArrayList;

/**
 * Ralentisseur qui étend la classe Ennemi, contient les mêmes attributs que Ennemi. Il ralentit la cadence d'attaque des tours autour de lui.
 */
public class Ralentisseur extends Ennemi {
    private int ralentissement;

    public Ralentisseur(Environnement env, ArrayList<Point> chemin) {
        super(10, 4, 10, env, chemin);
        this.ralentissement = 8;
    }

    @Override
    public void effectueAction() {
        super.effectueAction();
        for(Batiment batiment : this.getEnvironnement().getBatiments()) {
            if (Math.abs(this.getX() - batiment.getX()) <= 10 && Math.abs(this.getY() - batiment.getY()) <= 10) {
                ((BatimentTir) batiment).setCadenceTir(this.ralentissement);
            }
        }
    }
}
