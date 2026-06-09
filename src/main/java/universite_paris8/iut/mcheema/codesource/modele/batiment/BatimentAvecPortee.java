package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCammoufle;

public abstract class BatimentAvecPortee extends Batiment {
    private int portee;

    public BatimentAvecPortee(String nom, double x, double y, int portee, int prix, int nivMax, Environnement env) {
        super(nom, x, y, prix, nivMax, env);
        this.portee = portee;
    }

    public int getPortee() {
        return this.portee;
    }

    public void setPortee(int portee) {
        this.portee = portee;
    }

    /**
     * Recherche l'ennemi le plus proche de la tour parmi
     * tous les ennemis situés dans sa porté.
     * @return l'ennemi le plus proche dans la portée de la tour ou null si
     * aucun ennemi n'est dans sa portéee
     */
    public Ennemi ennemiDansPortee() {
        Ennemi ennemiRetourne = null;
        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
            if (this.calculDistance(ennemi) <= this.getPortee()) {
                if (!(ennemi instanceof EnnemiCammoufle)) {
                    if (ennemiRetourne == null) {
                        ennemiRetourne = ennemi;
                    }
                    else {
                        double distActu = calculDistance(ennemiRetourne);
                        double distNouv = calculDistance(ennemi);
                        if (distNouv < distActu) {
                            ennemiRetourne = ennemi;
                        }
                    }
                }
            }
        }
        return ennemiRetourne;
    }
    public String toString() {
        return  super.toString() +
                "\nPortee : " + this.getPortee();
    }

}
