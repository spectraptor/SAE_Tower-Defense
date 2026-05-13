package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.Bug;
import universite_paris8.iut.mcheema.codesource.modele.Ennemi;
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
    private Pane panePrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.environnement = new Environnement(640,480);
        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur()* Terrain.PIXEL_TUILLE,this.environnement.getTerrainDeJeu().obtenirHauteur()*Terrain.PIXEL_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(),this.tilePane);
        terrainVue.initialiseTerrainJeu();
        Ennemi ennemi = new Bug(this.environnement);
        this.environnement.ajouterEnnemi(ennemi);
    }

    public void deplace(KeyEvent e) {
        for (Ennemi ennemi : this.environnement.getEnnemis()) {
            ennemi.seDeplacer(e);
            System.out.println(ennemi);
        }
    }
}