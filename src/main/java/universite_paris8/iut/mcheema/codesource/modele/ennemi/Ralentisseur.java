package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentTir;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;

import java.util.ArrayList;

/**
 * Ralentisseur qui étend la classe Ennemi, contient les mêmes attributs que Ennemi. Il ralentit la cadence d'attaque des tours autour de lui.
 */
public class Ralentisseur extends Ennemi {
    private int ralentissement;
    private int portee;
    private ArrayList<BatimentTir> batimentRalenti;
    public Ralentisseur(Environnement env, ArrayList<Point> chemin) {
        super(10, 4, 10, env, chemin);
        this.ralentissement = 3;
        this.portee = 2 * Terrain.TAILLE_TUILLE;
        this.batimentRalenti = new ArrayList<>();
    }

    @Override
    public void effectueAction() {
        super.effectueAction();
        for(Batiment batiment : this.getEnvironnement().getBatiments()) {
            if(batiment instanceof BatimentTir) {
                if (!batimentRalenti.contains(batiment)) {
                    if (Math.sqrt((this.getX() - batiment.getX()) * (this.getX() - batiment.getX()) + (this.getY() - batiment.getY()) * (this.getY() - batiment.getY())) <= this.portee) {
                        ((BatimentTir) batiment).setCadenceTir(((BatimentTir) batiment).getCadenceTir() * this.ralentissement);
                        this.batimentRalenti.add((BatimentTir) batiment);
                    }
                }
            }
        }

        for(int i = this.batimentRalenti.size() - 1;i>=0;i--) {
            BatimentTir batiment = this.batimentRalenti.get(i);
            if (Math.sqrt((this.getX() - batiment.getX()) * (this.getX() - batiment.getX()) + (this.getY() - batiment.getY()) * (this.getY() - batiment.getY()))>this.portee) {
                batiment.setCadenceTir(batiment.getCadenceTir() / this.ralentissement);
                this.batimentRalenti.remove(i);
            }
        }
    }
}
