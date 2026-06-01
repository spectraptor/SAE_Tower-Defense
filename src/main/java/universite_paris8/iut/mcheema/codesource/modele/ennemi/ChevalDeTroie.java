package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
/**
 * ChevalDeTroie qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */

public class ChevalDeTroie extends Ennemi {

    public ChevalDeTroie(Environnement env) {
        super(3, 5, 4, env);
    }
    public ChevalDeTroie(int x, int y, Environnement env) {
        super(x, y, 3, 5, 4, env);
    }

    @Override
    public boolean estCamoufle() {
        return true;
    }
}
