package universite_paris8.iut.mcheema.codesource.modele;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BFS {
    /**
     * Le graphe (ou grille) sur lequel on travaille
     */
    private Terrain terrain;
    /**
     * Le sommet source de l'algo
     */
    private Tuile source;
    /**
     * Liste des sommets de la composante connexe de g obtenue par un parcours en largeur depuis le sommet source
     */
    private ArrayList<Tuile> parcours;
    /**
     * Chaque sommet (clé) est associé à son prédécesseur (valeur) du parcours en largeur
     */
    private Map<Tuile, Tuile> predecesseurs;

    public BFS(Terrain terrain, Tuile source) {
        this.terrain = terrain;
        this.source = source;
        parcours = new ArrayList<>();
        predecesseurs = new HashMap<Tuile, Tuile>();
        algoBFS();
    }

    /**
     * Exécute l'algo BFS sur g à partir du sommet source Remplit la liste parcours avec les sommets dans l'ordre de
     * visite Remplit la map predecesseurs en indiquant quel est le prédécesseur de chaque sommet Le prédécesseur du
     * sommet source est le sommet null
     */

    private void algoBFS() {
        LinkedList<Tuile> fifo = new LinkedList<>();
        parcours.add(source);
        predecesseurs.put(source, null);
        fifo.add(source);
        while (!fifo.isEmpty()) {
            Tuile s = fifo.poll();
            for (Tuile t : terrain.adjacents(s)) {
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

    public ArrayList<Tuile> cheminVersSource(Tuile cible) {
        ArrayList<Tuile> chemin = new ArrayList<>();

        if (!predecesseurs.containsKey(cible)) {
            return chemin;
        }
        Tuile courant = cible;
        while (courant != null) {
            chemin.add(0, courant);
            courant = predecesseurs.get(courant);
        }

        return chemin;
    }

    /*************************************************
     **** Pas de modifications à faire ci-dessous ****
     *************************************************/

    public ArrayList<Tuile> getParcours() {
        return parcours;
    }

    public Map<Tuile, Tuile> getPredecesseurs() {
        return predecesseurs;
    }

    /**
     * Mise à jour quand la source est modifiée suite à un clic droit
     *
     * @param source
     *            le nouveau sommet source
     */
    public void setSource(Tuile source) {
        this.source = source;
        clear();
        algoBFS();
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