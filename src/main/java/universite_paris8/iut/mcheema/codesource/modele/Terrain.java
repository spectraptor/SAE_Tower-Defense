package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

/**
 * La classe Terrain connait la liste des différents terrains (les tuiles, la longueur, la largueur, la taille d'une tuile)
 */

public class Terrain {
    public final static int TAILLE_TUILLE = 32;
    private int niveauTerrain;
    private char[][] terrainDeJeu;

    public Terrain(int levelTerrain) {
        this.niveauTerrain = levelTerrain;
        this.initialiseTerrain();
    }

    public void initialiseTerrain() {
        /*
        h = herbe
        t = terre
        e = glitch
        1 = ch_gauche_droite  →
        2 = ch_haut_bas ↓
        3 = ch_droite_bas ↱
        4 = ch_bas_gauche ↴
        5 = ch_gauche_haut ↲
        6 = ch_haut_droite ↳
        7 = ch_haut_droite_bas
        8 = ch_droite_bas_gauche
        9 = ch_bas_gauche_haut
        0 = ch_gauche_haut_droite
        b = base
         */
        switch (this.niveauTerrain) {
            case 1:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','h','h','h','e','e','e','e','h','h','h','h','e','e','e'},
                                                 {'e','e','e','e','e','h','h','h','h','h','e','3','1','1','4','h','h','h','h','h'},
                                                 {'e','e','e','e','e','h','h','h','h','h','h','2','h','h','2','h','h','3','1','1'},
                                                 {'e','e','e','e','h','h','h','h','h','h','h','2','h','h','2','h','h','2','h','h'},
                                                 {'e','3','1','1','1','1','1','1','1','4','e','2','e','e','6','1','1','5','e','e'},
                                                 {'e','2','h','h','h','h','e','e','e','2','e','2','e','e','e','h','h','h','e','e'},
                                                 {'h','2','h','3','1','1','4','e','e','2','e','2','e','e','e','e','h','e','e','e'},
                                                 {'h','2','h','2','h','h','2','h','e','2','e','6','1','1','1','1','1','4','e','e'},
                                                 {'h','6','1','5','h','h','2','h','h','2','h','h','h','h','h','h','h','2','e','e'},
                                                 {'h','h','h','h','h','h','2','h','h','2','h','h','h','h','h','h','h','2','e','e'},
                                                 {'b','1','1','1','1','1','5','h','h','6','1','1','1','1','1','1','1','5','e','e'},
                                                 {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','e','e','e','e','e'},
                                                 {'h','h','h','h','h','h','h','h','h','h','h','h','h','e','e','e','e','e','e','e'}
                };
                break;
            case 2:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','2','e','e','e','e'},
                                                 {'h','h','h','h','h','h','h','e','e','e','e','e','h','h','h','2','h','h','h','h'},
                                                 {'h','h','h','h','h','3','1','1','1','1','1','1','1','4','h','2','h','h','h','h'},
                                                 {'h','3','1','1','1','5','e','e','e','e','h','h','h','2','h','6','1','1','4','h'},
                                                 {'h','2','h','h','h','e','e','3','1','1','1','1','1','5','h','h','h','h','2','h'},
                                                 {'h','2','h','3','1','4','e','2','e','h','h','h','h','h','h','h','h','h','2','h'},
                                                 {'h','2','h','2','h','2','e','2','e','h','h','3','1','1','1','1','1','1','5','h'},
                                                 {'b','5','h','2','h','2','e','6','1','4','h','2','h','h','h','h','h','h','h','h'},
                                                 {'h','h','h','2','h','6','4','e','e','6','1','5','h','3','1','1','1','1','4','h'},
                                                 {'h','h','h','2','h','h','2','e','e','h','h','h','h','2','h','h','h','h','2','h'},
                                                 {'h','h','h','2','h','h','6','1','1','1','1','1','1','5','h','3','1','1','5','h'},
                                                 {'b','1','1','5','h','h','h','e','e','h','h','h','h','h','h','2','h','h','h','h'},
                                                 {'h','h','h','h','h','h','h','e','e','e','e','h','h','3','1','5','h','h','h','h'},
                                                 {'h','h','h','h','h','h','h','e','e','e','e','e','h','2','h','h','h','h','h','h'},
                                                 {'h','h','h','h','e','e','e','e','e','e','e','h','h','2','h','h','h','h','h','h'}
                };
                break;
            case 3:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','h','h','h','h','h','h','h','h','h','e','e','e','e','e','e','e','e'},
                                                 {'e','e','h','h','3','1','1','1','1','1','4','h','h','e','e','e','e','e','e','e'},
                                                 {'e','e','h','3','5','h','h','h','h','h','6','4','h','h','h','e','e','e','e','e'},
                                                 {'e','e','h','2','h','h','h','h','h','h','h','6','4','h','h','h','h','h','e','e'},
                                                 {'e','e','h','7','1','1','1','1','1','b','h','h','7','1','1','1','1','1','1','1'},
                                                 {'e','e','h','2','h','h','h','h','h','h','h','3','5','h','h','h','h','h','e','e'},
                                                 {'e','e','h','6','4','h','h','h','h','h','3','5','h','h','h','e','e','e','e','e'},
                                                 {'e','e','h','h','6','1','1','1','1','1','5','h','h','e','e','e','e','e','e','e'},
                                                 {'e','e','e','h','h','h','h','h','h','h','h','h','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'}};
                break;
        }
    }

    public int obtenirHauteur() {
        return this.terrainDeJeu.length;
    }

    public int obtenirLargeur() {
        return this.terrainDeJeu[0].length;
    }

    public char avoirCodeTuile(int i, int j) {
        return this.terrainDeJeu[i][j];
    }


    /**
     * Convertis les coordoonnées x et y en numéro de ligne et colonne de la tuile.
     * @param x les coordonnéees x
     * @param y les coordonnées y
     * @return un tableau contenant le numéro de ligne et colonne de la tuile.
     */
    public int[] convertirCoordsTuile(int x, int y)  {
        int[] tuile = new int[2];

        int ligne = y / TAILLE_TUILLE;
        int colonne = x / TAILLE_TUILLE;

        tuile[0] = ligne;
        tuile[1] = colonne;

        return tuile;
    }

    public boolean estDansTerrain(int nCol, int nLigne) {
        return nCol >= 0 && nCol < obtenirLargeur() && nLigne >= 0 && nLigne < obtenirHauteur();
    }


    /**
     * Regarde si la tuile est une tuile accessible pour les déplacements ennemi
     * @param nouveauX l'abscisse en pixels
     * @param nouveauY l'ordonnée en pixels
     * @return vrai si la tuile est accessible, faux sinon
     */
    public boolean tuileEstAccessibleCoords(int nouveauX, int nouveauY) {
        /*
         * On regarde avant si les déplacements sortent de la tuile
         * Autrement, l'appel à avoirCodeTuile accèdera à des indices hors limites.
         */

        int ligne = nouveauY / TAILLE_TUILLE;
        int colonne = nouveauX / TAILLE_TUILLE;
        return (avoirCodeTuile(ligne,colonne) >= '0' && avoirCodeTuile(ligne,colonne) <= '9') || avoirCodeTuile(ligne,colonne) == 'b';
    }

    public boolean tuileTourPosable(int x, int y) {
        int ligne = y / TAILLE_TUILLE;
        int colonne = x / TAILLE_TUILLE;
        return (!tuileEstAccessibleCoords(x,y) && avoirCodeTuile(ligne,colonne) != 'e');
    }


    public ArrayList<Point> adjacents(Point t) {
        ArrayList<Point> voisins = new ArrayList<>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // {colonne, ligne}
        for (int[] direction : directions) {
            int nCol = t.getColonne() + direction[0];
            int nLigne = t.getLigne() + direction[1];
            if (estDansTerrain(nCol, nLigne) && tuileEstAccessibleCoords(nCol * TAILLE_TUILLE, nLigne * TAILLE_TUILLE)) {
                voisins.add(new Point(nCol, nLigne));
            }
        }
        return voisins;
    }

}