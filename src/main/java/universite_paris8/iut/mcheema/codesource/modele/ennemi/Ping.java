package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class Ping extends Ennemi {

    public Ping(Environnement env) {
        super(1, 5, 2, env);
    }
    public Ping(int x, int y, Environnement env) {
        super(x, y, 1, 5, 2, env);
    }

}
