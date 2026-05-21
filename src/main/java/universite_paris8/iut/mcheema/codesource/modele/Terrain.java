package universite_paris8.iut.mcheema.codesource.modele;

/*

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
        e = eau
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
         */
        switch (this.niveauTerrain) {
            case 1:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','h','h','h','e','e','e','e','h','h','h','h','e','e','e'},
                                                 {'e','e','e','e','e','h','h','h','h','h','e','3','1','1','4','h','h','h','h','h'},
                                                 {'e','e','e','e','e','h','h','h','h','h','h','2','h','h','2','h','h','3','1','1'},
                                                 {'e','e','e','e','h','h','h','h','h','h','h','2','h','h','2','h','h','2','h','h'},
                                                 {'e','3','1','1','1','1','1','1','1','4','h','2','e','e','6','1','1','5','e','e'},
                                                 {'e','2','h','h','h','h','e','e','e','2','h','2','e','e','e','h','h','h','e','e'},
                                                 {'h','2','h','3','1','1','4','e','e','2','h','2','e','e','e','h','h','h','e','e'},
                                                 {'h','2','h','2','h','h','2','h','e','2','h','6','1','1','1','1','1','4','e','e'},
                                                 {'h','6','1','5','h','h','2','h','h','2','h','h','h','h','h','h','h','2','h','e'},
                                                 {'h','h','h','h','h','h','2','h','h','2','h','h','h','h','h','h','h','2','h','e'},
                                                 {'1','1','1','1','1','1','5','h','h','6','1','1','1','1','1','1','1','5','h','e'},
                                                 {'h','h','h','h','h','h','h','e','e','h','h','h','h','e','e','e','h','h','h','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'}
                };
                break;
            case 2:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','2','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','h','2','h','e','e','e'},
                                                 {'e','h','h','h','h','3','1','1','1','1','1','1','1','4','h','2','h','h','e','e'},
                                                 {'h','3','1','1','1','5','e','e','e','e','h','h','h','2','h','6','1','1','4','e'},
                                                 {'h','2','h','h','h','h','h','3','1','1','1','1','1','5','h','h','h','h','2','h'},
                                                 {'h','2','h','3','1','4','h','2','e','h','h','h','h','h','h','h','h','h','2','h'},
                                                 {'h','2','h','2','h','2','h','2','e','h','h','3','1','1','1','1','1','1','5','h'},
                                                 {'1','5','h','2','h','2','h','6','1','4','h','2','h','h','h','h','h','h','h','h'},
                                                 {'h','h','h','2','h','6','4','e','e','6','1','5','h','3','1','1','1','1','4','e'},
                                                 {'h','h','h','2','h','h','2','e','e','h','h','h','h','2','h','h','h','h','2','e'},
                                                 {'h','h','h','2','h','h','6','1','1','1','1','1','1','5','h','3','1','1','5','e'},
                                                 {'1','1','1','5','h','h','h','e','e','h','h','h','h','h','h','2','h','e','e','e'},
                                                 {'h','h','h','h','h','h','h','e','e','e','e','h','h','3','1','5','e','e','e','e'},
                                                 {'h','h','h','h','h','h','e','e','e','e','e','e','h','2','h','h','e','e','e','e'},
                                                 {'h','h','h','e','e','e','e','e','e','e','e','e','e','2','e','e','e','e','e','e'}
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
                                                 {'e','e','h','7','1','1','1','1','1','1','h','h','7','1','1','1','1','1','1','1'},
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
        return this.terrainDeJeu[1].length;
    }

    public char avoirCodeTuile(int i, int j) {
        return this.terrainDeJeu[i][j];
    }

    public boolean estDansTerrain(int x, int y) {
        return x >= 0 && x < obtenirLargeur()*TAILLE_TUILLE && y >= 0 && y < obtenirHauteur()*TAILLE_TUILLE;
    }


}