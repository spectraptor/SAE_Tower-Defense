package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    private Environnement environnement;

    @FXML
    private TilePane tilePane;

    @FXML
    private BorderPane borderPanePrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.environnement = new Environnement(640,480);
        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur()* Terrain.PIXEL_TUILLE,this.environnement.getTerrainDeJeu().obtenirHauteur()*Terrain.PIXEL_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(),this.tilePane);
        terrainVue.initialiseTerrainJeu();
    }
}