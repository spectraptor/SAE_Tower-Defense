package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;

/**
 * La classe à pour tâche de s'occuper de l'affichage des personnages et de la création de leur sprite.
 * Elle s'occupe d'un ennemi qu'elle reçoit.
 */
public class EnnemiVue {
    private Ennemi ennemi;
    private Pane paneJeu;

    public EnnemiVue(Ennemi ennemi, Pane paneJeu) {
        this.ennemi = ennemi;
        this.paneJeu = paneJeu;
    }

    public void creerSpriteEnnemi() {
        Image imgBogue = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/bogue.png").
                toExternalForm());
        Image imgChevalTroie = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/cheval_troie.png").
                toExternalForm());
        Image imgDrone = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/drone.png").
                toExternalForm());
        Image imgFreeze = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/flocon_neige.png").
                toExternalForm());
        Image imgPing = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/ping.png").
                toExternalForm());
        Image imgGrosBogue = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/gros_bogue.png").
                toExternalForm());
        Image imgErreurExec = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/ping.png").
                toExternalForm());
        Image imgErreurLog = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/ping.png").
                toExternalForm());
        Image imgErreurSynt = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/ping.png").
                toExternalForm());

        Label labelArgent = new Label(Integer.toString(this.ennemi.getArgentDonne()));

        ImageView ennemiImgView;

        if (this.ennemi instanceof Bogue)
            ennemiImgView = new ImageView(imgBogue);
        else if (this.ennemi instanceof ChevalDeTroie)
            ennemiImgView = new ImageView(imgChevalTroie);
        else if (this.ennemi instanceof DroneEspion)
            ennemiImgView = new ImageView(imgDrone);
        else if (this.ennemi instanceof Ralentisseur)
            ennemiImgView = new ImageView(imgFreeze);
        else if (this.ennemi instanceof Ping)
            ennemiImgView = new ImageView(imgPing);
        else if (this.ennemi instanceof GrosBogue)
            ennemiImgView = new ImageView(imgGrosBogue);
        else if (this.ennemi instanceof ErreurExecution)
            ennemiImgView = new ImageView(imgErreurExec);
        else if (this.ennemi instanceof ErreurDeLogique)
            ennemiImgView = new ImageView(imgErreurLog);
        else
            ennemiImgView = new ImageView(imgErreurSynt);

        ennemiImgView.setId(ennemi.getId());

        ennemiImgView.translateXProperty().bind(ennemi.xProperty().subtract(Terrain.TAILLE_TUILLE / 2));
        ennemiImgView.translateYProperty().bind(ennemi.yProperty().subtract(Terrain.TAILLE_TUILLE / 2));

        labelArgent.translateXProperty().bind(ennemi.xProperty().subtract(7));
        labelArgent.translateYProperty().bind(ennemi.yProperty().subtract(30));

        paneJeu.getChildren().add(1, ennemiImgView);
        paneJeu.getChildren().add(1, labelArgent);

        labelArgent.setTextFill(Color.DARKGOLDENROD);
        labelArgent.setId(this.ennemi.getId() + "A");
        labelArgent.setVisible(false);

    }
}
