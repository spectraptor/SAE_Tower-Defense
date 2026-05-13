package universite_paris8.iut.mcheema.codesource;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    private Terrain map;

    @FXML
    private TilePane tileMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.map = new Terrain();
        this.tileMap.setPrefSize(this.map.largeurMap()*Terrain.PIXEL_TUILLE,this.map.hauteurMap()*Terrain.PIXEL_TUILLE);
        this.intialiseMap();
    }

    public void intialiseMap() {
        Image imgHerbe = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/herbe.png").toExternalForm());
        Image imgTerre = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/terre.png").toExternalForm());
        for (int i = 0;i<this.map.hauteurMap();i++) {
            for (int j = 0;j<this.map.largeurMap();j++) {
                switch (this.map.avoirCodeMap(i,j)) {
                    case 'h':
                        this.tileMap.getChildren().add(new ImageView(imgHerbe));
                        break;
                    case 't':
                        this.tileMap.getChildren().add(new ImageView(imgTerre));
                        break;
                }
            }
        }
    }
}