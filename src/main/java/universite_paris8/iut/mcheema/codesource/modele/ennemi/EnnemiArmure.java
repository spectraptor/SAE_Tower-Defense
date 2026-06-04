package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;

import java.util.ArrayList;

public abstract class EnnemiArmure extends Ennemi {
    private int armure;
    public EnnemiArmure(int pv, int vitesse, int argentDonne, Environnement env, ArrayList<Point> chemin,int armure) {
        super(pv, vitesse, argentDonne, env, chemin);
        this.armure = armure;
    }
}
