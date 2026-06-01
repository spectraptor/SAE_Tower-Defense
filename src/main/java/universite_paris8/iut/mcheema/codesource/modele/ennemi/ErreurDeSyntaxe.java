package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;


/**
 * ErreurDeSyntaxe qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */
public class ErreurDeSyntaxe extends Ennemi {
    public ErreurDeSyntaxe(Environnement env) {super(3,1 ,4 , env);}

    public ErreurDeSyntaxe(int x, int y, Environnement env) {
        super(x, y, 3,1 ,4 , env);
    }

}
