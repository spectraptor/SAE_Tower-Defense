package universite_paris8.iut.mcheema.codesource.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public abstract class BatimentPacifiste extends Batiment{

    public BatimentPacifiste(int x, int y, int portee, Environnement env) {
        super(x, y, portee, env);
    }

    public abstract void effectueAction();
}
