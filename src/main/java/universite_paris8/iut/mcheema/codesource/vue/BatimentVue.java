package universite_paris8.iut.mcheema.codesource.vue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.batiment.*;

/**
 * La classe BatimentVue s'occupe de la création et l'affichage de sprites liées au bâtiment.
 * Elle détient un bâtiment, qu'elle va associer à une texture, qui va correspondre au sprite.
 */
public class BatimentVue {
    private Batiment batiment;
    private Pane paneJeu;
    private SonVue sonVue;

    public BatimentVue(Batiment bat, Pane paneJ, SonVue sonVue) {
        this.batiment = bat;
        this.paneJeu = paneJ;
        this.sonVue = sonVue;
    }

    public void creerSpriteBatiment() {
        Image imgCompilateur = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/compilateur.png").toExternalForm());
        Image imgCloud = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/cloud.png").toExternalForm());
        Image imgDebugger = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/debugger.png").toExternalForm());
        Image imgBombeLogique = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/bombeLogique.png").toExternalForm());
        Image imgSurcadence = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/surcadence.png").toExternalForm());
        Image imgRAM = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/batiments/ram.png").toExternalForm());
        ImageView imgVueBatiment;
        if(this.batiment instanceof Compilateur) {
            imgVueBatiment = new ImageView(imgCompilateur);
        }
        else if(this.batiment instanceof Cloud) {
            imgVueBatiment = new ImageView(imgCloud);
        }
        else if(this.batiment instanceof Debugger) {
            imgVueBatiment = new ImageView(imgDebugger);
        }
        else if(this.batiment instanceof BombeLogique) {
            imgVueBatiment = new ImageView(imgBombeLogique);
        }
        else if (this.batiment instanceof Surcadence) {
            imgVueBatiment = new ImageView(imgSurcadence);
        }
        else {
            imgVueBatiment = new ImageView(imgRAM);
        }
        imgVueBatiment.setId(this.batiment.getId());
        imgVueBatiment.translateXProperty().bind(this.batiment.xProperty().subtract(Terrain.TAILLE_TUILLE/2));
        imgVueBatiment.translateYProperty().bind(this.batiment.yProperty().subtract(Terrain.TAILLE_TUILLE/2));
        this.paneJeu.getChildren().add(1, imgVueBatiment);


        //INFO SUR LA TOUR

        imgVueBatiment.setOnMouseClicked(e -> {
            InfoBatimentVue infoBatimentVue = new InfoBatimentVue(this.batiment, this.paneJeu, this.sonVue);
            infoBatimentVue.afficheInfoBatiment();
        });



    }
}
