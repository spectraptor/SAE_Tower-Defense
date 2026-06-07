package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public abstract class BatimentPacifiste extends BatimentAvecPortee{

    public BatimentPacifiste(String nom,int x, int y, int portee,int prix, Environnement env) {
        super(nom,x, y, portee,prix, env);
    }

    public abstract void effectueAction();

}
