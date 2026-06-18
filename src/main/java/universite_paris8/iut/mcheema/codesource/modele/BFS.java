package universite_paris8.iut.mcheema.codesource.modele;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/*
Le BFS est l'algorithme qui permet de trouver et renvoyer le chemin le plus court.
 */
public class BFS {

    private Terrain terrain;
    private Point source;
    private ArrayList<Point> parcours;
    private Map<Point, Point> predecesseurs;

    public BFS(Terrain terrain, Point source) {
        this.terrain = terrain;
        this.source = source;
        parcours = new ArrayList<>();
        predecesseurs = new HashMap<Point, Point>();
        algoBFS();
    }

    /**
     * Exécute l'algo BFS sur g à partir du sommet source Remplit la liste parcours avec les sommets dans l'ordre de
     * visite Remplit la map predecesseurs en indiquant quel est le prédécesseur de chaque sommet Le prédécesseur du
     * sommet source est le sommet null
     */

    private void algoBFS() {
        LinkedList<Point> fifo = new LinkedList<>();
        parcours.add(source);
        predecesseurs.put(source, null);
        fifo.add(source);
        while (!fifo.isEmpty()) {
            Point s = fifo.poll();
            for (Point t : terrain.adjacents(s)) {
                if (!parcours.contains(t)) {
                    parcours.add(t);
                    predecesseurs.put(t, s);
                    fifo.add(t);
                }
            }
        }
    }

    /**
     * Retourne une liste donnant la suite des sommets depuis la cible jusqu'à la source
     *
     * @param cible
     * @return le chemin sous forme de liste de sommets
     */

    public ArrayList<Point> cheminDepuisSource(Point cible) {
        ArrayList<Point> chemin = new ArrayList<>();

        if (!predecesseurs.containsKey(cible)) {
            return chemin;
        }

        Point courant = cible;
        while (courant != null) {
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }
        return chemin;
    }


    /**
     * Mise à jour suite au changement de graphe
     *
     * @param terrain
     *            le nouveau graphe
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
        clear();
        algoBFS();
    }

    private void clear() {
        this.parcours.clear();
        this.predecesseurs.clear();
    }

}