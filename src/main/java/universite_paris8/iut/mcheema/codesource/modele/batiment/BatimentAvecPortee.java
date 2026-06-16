package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

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
        return ennemiRetourne;
    }
    public String avoirDescription() {
        return  super.avoirDescription() +
                "Portee : " + this.getPortee();
    }

    public void ameliorerBatiment() {
        if(this.getEnvironnement().getArgent() >= this.coutProchaineAmelioration()) {
            if (this.getNiveau() < this.getNiveauMax()) {
                this.portee = (int)(this.portee * (1 + this.pourcentageReduction()));
                this.getEnvironnement().retirerArgent(this.coutProchaineAmelioration());
                this.incrementerNiveau();
            }
        }
    }

}
