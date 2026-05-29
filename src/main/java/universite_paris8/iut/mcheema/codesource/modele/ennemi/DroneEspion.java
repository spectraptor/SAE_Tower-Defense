package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class DroneEspion extends Ennemi {
    public DroneEspion(Environnement env) {
        super(2, 3, 3, env);
    }
    public DroneEspion(int x, int y, Environnement env) {
        super(x, y, 2, 3, 3, env);
    }
}
