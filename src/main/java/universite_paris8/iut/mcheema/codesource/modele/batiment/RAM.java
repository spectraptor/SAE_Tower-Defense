package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public class RAM extends Batiment {
    private int argentDonne;
    private int tempsAtt;
    public RAM(int x, int y, Environnement env) {
        super("RAM", x, y, 150, env);
        this.argentDonne = 20;
        this.tempsAtt = 400;
    }

    @Override
    public void effectueAction() {
        if(this.getEnvironnement().getNbTours() % this.tempsAtt == 0) {
            this.getEnvironnement().setArgent(this.getEnvironnement().getArgent()+this.argentDonne);
        }
    }

    @Override
    public void ameliorerBatiment() {
        if(this.getEnvironnement().getArgent() >= this.coutProchaineAmelioration()) {
            this.setNiveau(this.getNiveau()+1);
            if (this.getNiveau() == 2) {
                this.argentDonne = 50;
                this.tempsAtt = 300;
            }
            this.getEnvironnement().setArgent(this.getEnvironnement().getArgent() - this.coutProchaineAmelioration());
        }
    }
}
