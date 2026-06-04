package universite_paris8.iut.mcheema.codesource.modele.batiment;


import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public abstract class BatimentTir extends Batiment{
    private double cadenceTir;

    public BatimentTir(int x, int y, int portee, int cTir, Environnement env) {
        super(x, y, portee, env);
        this.cadenceTir = cTir;
    }

    public void setCadenceTir(double cadenceTir) {
        this.cadenceTir = cadenceTir;
    }

    public double getCadenceTir() {
        return this.cadenceTir;
    }

    public abstract void effectueAction();
}
