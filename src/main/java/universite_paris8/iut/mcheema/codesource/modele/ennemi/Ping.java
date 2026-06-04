package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * Ping qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */
public class Ping extends Ennemi {
    public Ping(Environnement env, ArrayList<Point> chemin) {super(1, 5, 2, env, chemin);}
}
