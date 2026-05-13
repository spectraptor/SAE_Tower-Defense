package universite_paris8.iut.mcheema.codesource.modele;

public class Terrain {
    public final static int PIXEL_TUILLE = 32;
    private char[][] terrainDeJeu;

    public Terrain() {
        /*
        h = herbe
        t = terre
        e = eau
         */
        this.terrainDeJeu = new char[][]{{'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'},
                                         {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'},
                                         {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'},
                                         {'h','h','h','h','h','h','h','h','h','h','h','t','t','t','t','h','h','h','h','h'},
                                         {'h','h','h','h','h','h','h','h','h','h','h','t','h','h','t','e','e','t','t','t'},
                                         {'h','h','h','h','h','h','h','h','h','h','h','t','h','h','t','e','e','t','h','h'},
                                         {'h','t','t','t','t','t','t','t','t','t','h','t','h','h','t','t','t','t','h','h'},
                                         {'h','t','h','h','h','h','h','h','h','t','h','t','h','h','h','h','h','h','h','h'},
                                         {'h','t','h','t','t','t','t','h','h','t','h','t','h','h','h','h','h','h','h','h'},
                                         {'h','t','h','t','e','e','t','h','h','t','h','t','t','t','t','t','t','t','h','h'},
                                         {'h','t','t','t','e','e','t','h','h','t','h','h','h','h','h','h','h','t','h','h'},
                                         {'h','h','h','h','h','h','t','h','h','t','h','h','h','h','h','h','h','t','h','h'},
                                         {'t','t','t','t','t','t','t','h','h','t','t','t','t','t','t','t','t','t','h','h'},
                                         {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'},
                                         {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'}
        };
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
