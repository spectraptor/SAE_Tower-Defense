package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * Le Ping est un type d'ennemi du jeu.
 * C'est l'ennemi le plus rapide du jeu.
 */
public class Ping extends Ennemi {
    public Ping(Environnement env, ArrayList<Point> chemin) {super(2, 4, 10, env, chemin);}
}
