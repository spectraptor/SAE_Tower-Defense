package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class ErreurDeLogique extends Ennemi {
    public ErreurDeLogique(int x, int y, Environnement env) {
        super(x, y, 4, 3, 3, env);
    }
}
