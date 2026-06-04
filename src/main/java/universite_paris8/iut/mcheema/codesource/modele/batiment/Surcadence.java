
package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

import java.util.ArrayList;

/**
 * La classe Surcadence permet d'augmenter la vitesse d'attaque des tours à proximité
 * Portée : faible
 * Prix : moyen
 */
public class Surcadence extends BatimentPacifiste{
    private ArrayList<Batiment> batiments;

    public Surcadence(int x, int y, Environnement env) {
        super(x, y, 100, env);
        batiments = new ArrayList<>();
    }

    @Override
    public void effectueAction() {
        for (Batiment batiment : this.getEnvironnement().getBatiments()) {
            if (!batiments.contains(batiment)) {
                if (batiment instanceof BatimentTir && this.calculDistance(batiment) <= this.getPortee()) {
                    ((BatimentTir) batiment).setCadenceTir((int) (((BatimentTir) batiment).getCadenceTir() / 10 + 1));
                    batiments.add(batiment);
                }
            }
        }
    }
}
