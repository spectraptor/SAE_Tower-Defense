package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.*;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.vue.BaseVue;
import universite_paris8.iut.mcheema.codesource.vue.BatimentVue;
import universite_paris8.iut.mcheema.codesource.vue.EnnemiVue;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;
import java.net.URL;
import java.util.ResourceBundle;

/*

 */

public class Controleur implements Initializable {

    private Timeline gameLoop;

    private Environnement environnement;

    @FXML
    private TilePane tilePane;


    @FXML
    private Pane paneJeu;

    @FXML
    private Label labelVieBase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Base base = new Base(630,145);
        BaseVue baseVue = new BaseVue(this.paneJeu,base);
        baseVue.creeSpriteBase();
        this.labelVieBase.textProperty().bind(base.pvProperty().asString());
        this.environnement = new Environnement(1,base);

        this.environnement.getEnnemis().addListener(new ObservateurListeEnnemis(this));
        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur() * Terrain.TAILLE_TUILLE, this.environnement.getTerrainDeJeu().obtenirHauteur() * Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(), this.tilePane);
        for(int i = 0;i<200;i++) {
            Ennemi bug = new Bogue(372, 209, this.environnement); // (560, 140) si on veut essayer qu'il atteigne la fin
            this.environnement.ajouterEnnemi(bug);

            EnnemiVue bugVue = new EnnemiVue(bug, paneJeu);
            bugVue.creerSpriteEnnemi();
        }

        terrainVue.afficheTerrainJeu();

        this.initAnimation();
        this.gameLoop.play();

    }

    private void initAnimation() {
        for(Ennemi ennemi : this.environnement.getEnnemis()) {
            ennemi.attribuerDirectionAleatoire();
        }

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                ev -> {
                    environnement.unTour();
                    if(this.environnement.getEnnemis().isEmpty()) {
                        this.gameLoop.stop();
                        System.out.println("Vous avez gagné");
                    }
                    if(this.environnement.getBase().estDetruite()) {
                        this.gameLoop.stop();
                        System.out.println("Vous avez perdu");
                    }
                }
        );

        gameLoop.getKeyFrames().add(kf);
    }

    public Pane getPaneJeu() {
        return this.paneJeu;
    }


    @FXML
    public void ajouterTour(MouseEvent mouseEvent) {
        int coordsSourisX = (int)mouseEvent.getX();
        int coordsSourisY = (int)mouseEvent.getY();
        // On ne peut pas placer des bâtiments sur le chemin -> obstrue l'ennemi.
        if (!this.environnement.tuileEstAccessible(coordsSourisX, coordsSourisY) &&
                !this.environnement.estAdjacentATour(coordsSourisX, coordsSourisY)) {

            Batiment batiment = new Tour1(coordsSourisX, coordsSourisY, this.environnement);
            this.environnement.ajouterBatiment(batiment);

            System.out.println(batiment);

            BatimentVue batimentVue = new BatimentVue(batiment, this.paneJeu);
            batimentVue.creerSpriteBatiment();

        }
        System.out.println("Pos. souris : " + coordsSourisX + ";" + coordsSourisY);
    }
}