package universite_paris8.iut.mcheema.codesource.modele.batiment;


import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public abstract class BatimentTir extends BatimentAvecPortee{
    private int cadenceTir;
    private int degat;

    public BatimentTir(String nom ,double x, double y, int portee, int degat, int prix, int cTir, Environnement env) {
        super(nom,x, y, portee, prix, 4, env);
        this.cadenceTir = cTir;
        this.degat = degat;
    }

    public void setCadenceTir(int cadenceTir) {
        this.cadenceTir = cadenceTir;
    }

    public void effectueAction() {
        Ennemi ennemi = this.ennemiDansPortee();
        if (ennemi != null && this.peutAttaquer(ennemi)) {
            if (this.estCapableDeTirer()) {
                this.getEnvironnement().ajouterProjectile(this.choisirProjectile(ennemi));
            }
        }
    }

    public int getDegat() {
        return this.degat;
    }

    public double getCadenceTir() {
        return this.cadenceTir;
    }

    public void ameliorerBatiment() {
        if(this.getEnvironnement().getArgent() >= this.coutProchaineAmelioration()) {
            if (this.getNiveau() < this.getNiveauMax()) {
                super.ameliorerBatiment();
                this.cadenceTir = (int)(this.cadenceTir * (1 - this.pourcentageReduction()));
            }
        }
    }

    public abstract boolean peutAttaquer(Ennemi cible);

    public boolean estCapableDeTirer() {
        return this.getEnvironnement().getNbTours() % this.getCadenceTir() == 0;
    }

    public abstract Projectile choisirProjectile(Ennemi cible);

    public String avoirDescription() {
        return  super.avoirDescription() + "\nCadence : " +  this.cadenceTir +
                "\nDegat : " + this.getDegat();
    }
}
