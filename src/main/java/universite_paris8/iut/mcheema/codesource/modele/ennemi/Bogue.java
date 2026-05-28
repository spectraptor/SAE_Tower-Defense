package universite_paris8.iut.mcheema.codesource.modele.ennemi;

/*

 */

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class Bogue extends Ennemi {
    public Bogue(Environnement env) {super(2, 1, 2, env);}

    public Bogue(int x, int y, Environnement env) {super(x, y, 2, 1, 2, env);}

}
