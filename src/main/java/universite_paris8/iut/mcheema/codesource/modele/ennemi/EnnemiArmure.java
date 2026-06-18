package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;

import java.util.ArrayList;

/**
 * La classe EnnemiArmure est un type d'ennemi.
 * Les bâtiments enlèvent de la vie à l'ennemi armuré seulement si leurs dégâts sont supérieurs à l'armure qu'il détient.
 * L'armure n'est jamais réduite.
 */
public abstract class EnnemiArmure extends Ennemi {
    private int armure;

    public EnnemiArmure(int pv, int vitesse, int argentDonne, Environnement env, ArrayList<Point> chemin, int armure) {
        super(pv, vitesse, argentDonne, env, chemin);
        this.armure = armure;
    }

    @Override
    public void subirDegats(int degat) {
        if (this.getPv() + this.armure - degat <= 0)
            this.meurt();
        else {
            if(this.armure < degat) {
                this.setPv(this.getPv() + (this.armure - degat));
            }
        }
    }
}
