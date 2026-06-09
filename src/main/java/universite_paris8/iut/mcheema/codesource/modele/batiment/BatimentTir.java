package universite_paris8.iut.mcheema.codesource.modele.batiment;


import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public abstract class BatimentTir extends BatimentAvecPortee{
    private double cadenceTir;
    private int degat;

    public BatimentTir(String nom ,double x, double y, int portee, int degat, int prix, double cTir, Environnement env) {
        super(nom,x, y, portee, prix, 4, env);
        this.cadenceTir = cTir;
        this.degat = degat;
    }

    public void setCadenceTir(double cadenceTir) {
        this.cadenceTir = cadenceTir;
    }

    public int getDegat() {
        return this.degat;
    }

    public double getCadenceTir() {
        return this.cadenceTir;
    }

    public void ameliorerBatiment() {
        double pourcentage;
        if(this.getEnvironnement().getArgent() >= this.coutProchaineAmelioration()) {
            if (this.getNiveau() < this.getNiveauMax()) {
                this.cadenceTir *= 1 + this.pourcentageReduction();
                this.getEnvironnement().retirerArgent(this.coutProchaineAmelioration());
                this.incrementerNiveau();
            }
        }
    }

    public boolean estCapableDeTirer() {
        return this.getEnvironnement().getNbTours() % this.getCadenceTir() == 0;
    }

    public abstract Projectile choisirProjectile(Ennemi cible);

    public String toString() {
        return  super.toString() + "\nCadence : " + (int) this.cadenceTir +
                "\nDegat : " + this.getDegat();
    }
}
