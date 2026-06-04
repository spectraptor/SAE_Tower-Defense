package universite_paris8.iut.mcheema.codesource.modele.ennemi;

/**
 * Bogue qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

public class Bogue extends Ennemi {
    public Bogue(Environnement env, ArrayList<Point> chemin) {super(2, 5, 2, env, chemin);}
    public Bogue(int x, int y, Environnement env, ArrayList<Point> chemin) {super(2, 5, 2, env, chemin);}
}
