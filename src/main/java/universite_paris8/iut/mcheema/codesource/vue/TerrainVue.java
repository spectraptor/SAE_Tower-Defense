package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

public class TerrainVue {
    private Terrain terrainDeJeu;
    private TilePane tilePane;

    public TerrainVue(Terrain terrainDeJeu, TilePane tilePane) {
        this.terrainDeJeu = terrainDeJeu;
        this.tilePane = tilePane;
    }

    public void initialiseTerrainJeu() {
        Image imgHerbe = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/herbe.png").toExternalForm());
        Image imgTerre = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/terre.png").toExternalForm());
        Image imgEau = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/eau.png").toExternalForm());
        for (int i = 0; i < this.terrainDeJeu.obtenirHauteur(); i++) {
            for (int j = 0; j < this.terrainDeJeu.obtenirLargeur(); j++) {
                switch (this.terrainDeJeu.avoirCodeTuile(i,j)) {
                    case 'h':
                        this.tilePane.getChildren().add(new ImageView(imgHerbe));
                        break;
                    case 't':
                        this.tilePane.getChildren().add(new ImageView(imgTerre));
                        break;
                    case 'e':
                        this.tilePane.getChildren().add(new ImageView(imgEau));
                }
            }
        }
    }

}
