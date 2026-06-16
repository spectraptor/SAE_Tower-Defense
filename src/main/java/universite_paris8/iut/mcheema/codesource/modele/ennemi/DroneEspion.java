package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * DroneEspion qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */

public class DroneEspion extends EnnemiVolant {
    public DroneEspion(Environnement env, ArrayList<Point> chemin) {super(3, 3, 30, env, chemin);}
}
