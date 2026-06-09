package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.BFS;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.util.ArrayList;

/**
 GrosBogue qui étend la classe Ennemi, contient les mêmes attributs que Ennemi. Il crée 2 Bogues à l'emplacement de sa mort
 */
public class GrosBogue extends Ennemi {
  
    public GrosBogue(Environnement env, ArrayList<Point> chemin) {super(2, 1, 4, env, chemin);}

  

    @Override
    public void effectueAction() {
        super.effectueAction();
        if(this.getPv() <= 1 && !this.aAtteintDestination()) {
            Point basePoint2 = new Point(0, 11);
            Point entree2 = new Point((int)this.getX() / Terrain.TAILLE_TUILLE, (int)this.getY() / Terrain.TAILLE_TUILLE);
            BFS bfs2 = new BFS(this.getEnvironnement().getTerrainDeJeu(), basePoint2);
            ArrayList<Point> chemin2 = bfs2.cheminDepuisSource(entree2);
            this.getEnvironnement().getEnnemis().add(new Bogue(this.getX(),this.getY(),this.getEnvironnement(), chemin2));
            this.getEnvironnement().getEnnemis().add(new Bogue(this.getX(),this.getY()+5,this.getEnvironnement(), chemin2));
            this.meurt();
        }
    }
}
