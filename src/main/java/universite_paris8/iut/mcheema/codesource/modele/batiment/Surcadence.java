
package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.util.ArrayList;

/**
 * La classe Surcadence permet d'augmenter la vitesse d'attaque des tours à proximité
 * Portée : faible
 * Prix : moyen
 */
public class Surcadence extends BatimentAvecPortee{
    private ArrayList<BatimentTir> batiments;
    private double diviseCadence;

    public Surcadence(double x, double y, Environnement env) {
        super("Surcadence", x, y, Terrain.TAILLE_TUILLE,90, 2, env);
        batiments = new ArrayList<>();
        this.diviseCadence = 1.5;
    }

    @Override
    public void effectueAction() {
        for (Batiment batiment : this.getEnvironnement().getBatiments()) {
            if(batiment instanceof BatimentTir) {
                if (!batiments.contains(batiment) && ((BatimentTir) batiment).getCadenceTir() != ((BatimentTir) batiment).getCadenceTir() / this.diviseCadence) {
                    if (this.calculDistance(batiment) <= this.getPortee()) {
                        ((BatimentTir) batiment).setCadenceTir((((BatimentTir) batiment).getCadenceTir() / this.diviseCadence)); // la tour ne doit pas dépasser la cadence de de 6
                        batiments.add((BatimentTir)batiment);
                    }
                }
            }
        }
        for(int i = 0;i<this.batiments.size();i++) {
            if(this.calculDistance(this.batiments.get(i)) > this.getPortee()) {
                this.batiments.get(i).setCadenceTir(this.batiments.get(i).getCadenceTir() * this.diviseCadence);
                this.batiments.remove(this.batiments.get((i)));
            }
        }
    }

    @Override
    public void ameliorerBatiment() {
        if(this.getEnvironnement().getArgent() >= this.coutProchaineAmelioration()) {
            if (this.getNiveau() < this.getNiveauMax()) {
                this.diviseCadence *= 1 - this.pourcentageReduction();
                this.getEnvironnement().retirerArgent(this.coutProchaineAmelioration());
                this.incrementerNiveau();
                this.batiments.clear(); // enlever la liste des bâtiments déjà présents pour pouvoir changer leur cadence
            }
        }
    }
}
