package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * ErreurExecution qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */
public class ErreurExecution extends Ennemi {
    public ErreurExecution(Environnement env, ArrayList<Point> chemin) {super(100, 2, 1500, env, chemin);}
}
