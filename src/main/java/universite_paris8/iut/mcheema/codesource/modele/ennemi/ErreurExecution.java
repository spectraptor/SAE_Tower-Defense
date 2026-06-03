package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

/**
 * ErreurExecution qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */
public class ErreurExecution extends Ennemi {
    public ErreurExecution(Environnement env) {super(7, 2, 5, env);}

    public ErreurExecution(int x, int y, Environnement env) {super(x, y, 7, 2, 5, env);}
}
