package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * ErreurDeLogique qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */

public class ErreurDeLogique extends EnnemiArmure {
    public ErreurDeLogique(Environnement env, ArrayList<Point> chemin) {super(4, 3, 3, env, chemin,5);}
}
