package universite_paris8.iut.mcheema.codesource.modele;

public class Terrain {
    public final static int PIXEL_TUILLE = 32;
    private char[][] map;

    public Terrain() {
        /*
        h = herbe
        t = terre
         */
        this.map = new char[][]{{'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'},
                                {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'},
                                {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'},
                                {'h','h','h','h','h','h','h','h','h','h','h','t','t','t','t','h','h','h','h','h'},
                                {'h','h','h','h','h','h','h','h','h','h','h','t','h','h','t','h','h','t','t','t'},
                                {'h','h','h','h','h','h','h','h','h','h','h','t','h','h','t','h','h','t','h','h'},
                                {'h','t','t','t','t','t','t','t','t','t','h','t','h','h','t','t','t','t','h','h'},
                                {'h','t','h','h','h','h','h','h','h','t','h','t','h','h','h','h','h','h','h','h'},
                                {'h','t','h','t','t','t','t','h','h','t','h','t','h','h','h','h','h','h','h','h'},
                                {'h','t','h','t','h','h','t','h','h','t','h','t','t','t','t','t','t','t','h','h'},
                                {'h','t','t','t','h','h','t','h','h','t','h','h','h','h','h','h','h','t','h','h'},
                                {'h','h','h','h','h','h','t','h','h','t','h','h','h','h','h','h','h','t','h','h'},
                                {'t','t','t','t','t','t','t','h','h','t','t','t','t','t','t','t','t','t','h','h'},
                                {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'},
                                {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','h'}
                               };
    }

    public int hauteurMap() {
        return this.map.length;
    }

    public int largeurMap() {
        return this.map[1].length;
    }

    public char avoirCodeMap(int i, int j) {
        return this.map[i][j];
    }
}
