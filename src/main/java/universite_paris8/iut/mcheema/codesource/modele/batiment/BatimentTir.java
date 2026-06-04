package universite_paris8.iut.mcheema.codesource.modele.batiment;


import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public abstract class BatimentTir extends Batiment{
    private double cadenceTir;
    private int degat;
    public BatimentTir(String nom,int x, int y, int portee,int degat, int cTir, Environnement env) {
        super(nom,x, y, portee, env);
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

    public String toString() {
        return  super.toString() + "\nCadence : " + this.getCadenceTir() +
                "\nDegat : " + this.getDegat();
    }
}
