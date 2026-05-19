package universite_paris8.iut.mcheema.codesource.modele;

import java.awt.image.FilteredImageSource;

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
         */
        switch (this.niveauTerrain) {
            case 1:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','h','h','h','e','e','e','e','h','h','h','h','e','e','e'},
                                                 {'e','e','e','e','e','h','h','h','h','h','e','t','t','t','t','h','h','h','h','h'},
                                                 {'e','e','e','e','e','h','h','h','h','h','h','t','h','h','t','h','h','t','t','t'},
                                                 {'e','e','e','e','h','h','h','h','h','h','h','t','h','h','t','h','h','t','h','h'},
                                                 {'e','t','t','t','t','t','t','t','t','t','h','t','e','e','t','t','t','t','e','e'},
                                                 {'e','t','h','h','h','h','e','e','e','t','h','t','e','e','e','h','h','h','e','e'},
                                                 {'h','t','h','t','t','t','t','e','e','t','h','t','e','e','e','h','h','h','e','e'},
                                                 {'h','t','h','t','h','h','t','h','e','t','h','t','t','t','t','t','t','t','e','e'},
                                                 {'h','t','t','t','h','h','t','h','h','t','h','h','h','h','h','h','h','t','h','e'},
                                                 {'h','h','h','h','h','h','t','h','h','t','h','h','h','h','h','h','h','t','h','e'},
                                                 {'t','t','t','t','t','t','t','h','h','t','t','t','t','t','t','t','t','t','h','e'},
                                                 {'h','h','h','h','h','h','h','e','e','h','h','h','h','e','e','e','h','h','h','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'}
                };
                break;
            case 2:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','t','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','h','t','h','e','e','e'},
                                                 {'e','h','h','h','h','t','t','t','t','t','t','t','t','t','h','t','h','h','e','e'},
                                                 {'h','t','t','t','t','t','e','e','e','e','h','h','h','t','h','t','t','t','t','e'},
                                                 {'h','t','h','h','h','h','h','t','t','t','t','t','t','t','h','h','h','h','t','h'},
                                                 {'h','t','h','t','t','t','h','t','e','h','h','h','h','h','h','h','h','h','t','h'},
                                                 {'h','t','h','t','h','t','h','t','e','h','h','t','t','t','t','t','t','t','t','h'},
                                                 {'t','t','h','t','h','t','h','t','t','t','h','t','h','h','h','h','h','h','h','h'},
                                                 {'h','h','h','t','h','t','t','e','e','t','t','t','h','t','t','t','t','t','t','e'},
                                                 {'h','h','h','t','h','h','t','e','e','h','h','h','h','t','h','h','h','h','t','e'},
                                                 {'h','h','h','t','h','h','t','t','t','t','t','t','t','t','h','t','t','t','t','e'},
                                                 {'t','t','t','t','h','h','h','e','e','h','h','h','h','h','h','t','h','e','e','e'},
                                                 {'h','h','h','h','h','h','h','e','e','e','e','h','h','t','t','t','e','e','e','e'},
                                                 {'h','h','h','h','h','h','e','e','e','e','e','e','h','t','h','h','e','e','e','e'},
                                                 {'h','h','h','e','e','e','e','e','e','e','e','e','e','t','e','e','e','e','e','e'}
                };
                break;
            case 3:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','h','h','h','h','h','h','h','h','h','e','e','e','e','e','e','e','e'},
                                                 {'e','e','h','h','t','t','t','t','t','t','t','h','h','e','e','e','e','e','e','e'},
                                                 {'e','e','h','t','t','h','h','h','h','h','t','t','h','h','h','e','e','e','e','e'},
                                                 {'e','e','h','t','h','h','h','h','h','h','h','t','t','h','h','h','h','h','e','e'},
                                                 {'e','e','h','t','t','t','t','t','t','t','h','h','t','t','t','t','t','t','t','t'},
                                                 {'e','e','h','t','h','h','h','h','h','h','h','t','t','h','h','h','h','h','e','e'},
                                                 {'e','e','h','t','t','h','h','h','h','h','t','t','h','h','h','e','e','e','e','e'},
                                                 {'e','e','h','h','t','t','t','t','t','t','t','h','h','e','e','e','e','e','e','e'},
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


}