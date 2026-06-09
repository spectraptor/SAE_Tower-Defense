package universite_paris8.iut.mcheema.codesource.modele.ennemi;


import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * Bogue qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */
public class Bogue extends Ennemi {
    public Bogue(Environnement env, ArrayList<Point> chemin) {super(2, 2, 2, env, chemin);}
    public Bogue(double x, double y, Environnement env, ArrayList<Point> chemin) {super(2, 5, 2, env, chemin);}
}
