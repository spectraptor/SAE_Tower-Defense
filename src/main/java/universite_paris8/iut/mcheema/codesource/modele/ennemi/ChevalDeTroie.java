package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

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
