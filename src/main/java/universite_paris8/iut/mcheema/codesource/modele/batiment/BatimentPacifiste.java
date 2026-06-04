package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public abstract class BatimentPacifiste extends Batiment{

    public BatimentPacifiste(String nom,int x, int y, int portee, Environnement env) {
        super(nom,x, y, portee, env);
    }

    public abstract void effectueAction();

}
