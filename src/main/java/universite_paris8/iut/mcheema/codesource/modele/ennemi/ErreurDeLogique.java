package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

/**
 * ErreurDeLogique qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */

public class ErreurDeLogique extends Ennemi {
    public ErreurDeLogique(Environnement env) {super(4, 3, 3, env);}

    public ErreurDeLogique(int x, int y, Environnement env) {
        super(x, y, 4, 3, 3, env);
    }
}
