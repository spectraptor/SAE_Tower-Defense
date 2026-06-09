package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class RAM extends Batiment {
    private int argentDonne;
    private int tempsAtt;

    public RAM(double x, double y, Environnement env) {
        super("RAM", x, y, 150, 3, env);
        this.argentDonne = 20;
        this.tempsAtt = 400;
    }

    @Override
    public void effectueAction() {
        if(this.getEnvironnement().getNbTours() % this.tempsAtt == 0) {
            this.getEnvironnement().ajouterArgent(this.argentDonne);
        }
    }

    @Override
    public void ameliorerBatiment() {
        if (this.getEnvironnement().getArgent() >= this.coutProchaineAmelioration()) {
            if (this.getNiveau() < this.getNiveauMax()) {
                this.argentDonne = (int)(this.argentDonne * (1 + this.pourcentageReduction()));
                this.tempsAtt = (int) (this.tempsAtt * (1 - this.pourcentageReduction()));
                this.getEnvironnement().retirerArgent(this.coutProchaineAmelioration());
                this.incrementerNiveau();
            }
        }
    }

    public String avoirDescription() {
        return super.avoirDescription() + "Argent donné : " + this.argentDonne + "\nTemps d'attente : " + this.tempsAtt;
    }

}
