package universite_paris8.iut.mcheema.codesource.modele.batiment;


import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public abstract class BatimentTir extends BatimentAvecPortee{
    private double cadenceTir;
    private int degat;
    public BatimentTir(String nom,int x, int y, int portee,int degat,int prix, double cTir, Environnement env) {
        super(nom,x, y, portee,prix, env);
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

    public abstract void effectueAction();




    public void ameliorerBatiment() {
        if(this.getEnvironnement().getArgent() >= this.coutProchaineAmelioration()) {
            this.setNiveau(this.getNiveau()+1);
            if (this.getNiveau() == 2) {this.setCadenceTir(this.getCadenceTir() / 1.5);
            }
            this.getEnvironnement().setArgent(this.getEnvironnement().getArgent() - this.coutProchaineAmelioration());
        }
    }





    public abstract Projectile choisirProjectile(Ennemi cible);

    public String toString() {
        return  super.toString() + "\nCadence : " + (int) this.cadenceTir +
                "\nDegat : " + this.getDegat();
    }
}
