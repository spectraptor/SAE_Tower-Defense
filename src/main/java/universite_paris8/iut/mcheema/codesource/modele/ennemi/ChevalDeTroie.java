package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * ChevalDeTroie qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */

public class ChevalDeTroie extends Ennemi {

    public ChevalDeTroie(Environnement env, ArrayList<Point> chemin) {super(3, 1, 4, env, chemin);}

    @Override
    public boolean estCamoufle() {
        return true;
    }
}
