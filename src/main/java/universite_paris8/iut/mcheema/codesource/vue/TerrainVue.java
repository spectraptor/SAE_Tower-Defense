package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

/*

 */

public class TerrainVue {
    private Terrain terrainDeJeu;
    private TilePane tilePane;

    public TerrainVue(Terrain terrainDeJeu, TilePane tilePane) {
        this.terrainDeJeu = terrainDeJeu;
        this.tilePane = tilePane;
    }

    public void afficheTerrainJeu() {
        Image imgHerbe = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/herbe.png").toExternalForm());
        Image imgTerre = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/terre.png").toExternalForm());
        Image imgEau = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/eau.png").toExternalForm());
        Image ch_gauche_droite = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin1.png").toExternalForm());
        Image ch_haut_bas = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin2.png").toExternalForm());
        Image ch_droite_bas = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin3.png").toExternalForm());
        Image ch_bas_gauche = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin4.png").toExternalForm());
        Image ch_gauche_haut = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin5.png").toExternalForm());
        Image ch_haut_droite = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin6.png").toExternalForm());
        Image ch_haut_droite_bas = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin7.png").toExternalForm());
        Image ch_droite_bas_gauche = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin8.png").toExternalForm());
        Image ch_bas_gauche_haut = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin9.png").toExternalForm());
        Image ch_gauche_haut_droite = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/chemins/chemin10.png").toExternalForm());
        for (int i = 0; i < this.terrainDeJeu.obtenirHauteur(); i++) {
            for (int j = 0; j < this.terrainDeJeu.obtenirLargeur(); j++) {
                switch (this.terrainDeJeu.avoirCodeTuile(i,j)) {
                    case '1':
                        this.tilePane.getChildren().add(new ImageView(ch_gauche_droite));
                        break;
                    case '2':
                        this.tilePane.getChildren().add(new ImageView(ch_haut_bas));
                        break;
                    case '3':
                        this.tilePane.getChildren().add(new ImageView(ch_droite_bas));
                        break;
                    case '4':
                        this.tilePane.getChildren().add(new ImageView(ch_bas_gauche));
                        break;
                    case '5':
                        this.tilePane.getChildren().add(new ImageView(ch_gauche_haut));
                        break;
                    case '6':
                        this.tilePane.getChildren().add(new ImageView(ch_haut_droite));
                        break;
                    case '7':
                        this.tilePane.getChildren().add(new ImageView(ch_haut_droite_bas));
                        break;
                    case '8':
                        this.tilePane.getChildren().add(new ImageView(ch_droite_bas_gauche));
                        break;
                    case '9':
                        this.tilePane.getChildren().add(new ImageView(ch_bas_gauche_haut));
                        break;
                    case '0':
                        this.tilePane.getChildren().add(new ImageView(ch_gauche_haut_droite));
                        break;
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
