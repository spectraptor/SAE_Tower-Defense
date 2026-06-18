package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;

/**
 * La classe BatimentVue à pour tâche la création et l'affichage de sprites liées aux ennemis.
 * Elle détient un ennemi, qu'elle va associer à une texture, qui va correspondre au sprite.
 */
public class EnnemiVue {
    private Ennemi ennemi;
    private Pane paneJeu;

    public EnnemiVue(Ennemi ennemi, Pane paneJeu) {
        this.ennemi = ennemi;
        this.paneJeu = paneJeu;
    }

    public void creerSpriteEnnemi() {
        // Variables utilisées pour les boss : en effet, étant donné qu'ils sont plus grands, il faut ajuster leur affichage sur les tuiles.
        // Pour les ennemis normaux (pas des bosses), la valeur par défaut est de 0.
        int differenceX = 0;
        int differenceY = 0;

        Image imgBogue = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/bogue.png").toExternalForm());
        Image imgChevalTroie = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/cheval_troie.png").toExternalForm());
        Image imgDrone = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/drone.png").toExternalForm());
        Image imgFreeze = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/flocon_neige.png").toExternalForm());
        Image imgPing = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/ping.png").toExternalForm());
        Image imgGrosBogue = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/gros_bogue_b.png").toExternalForm());
        Image imgErreurExec = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/erreur_execution_b.png").toExternalForm());
        Image imgErreurLog = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/erreur_logique_b.png").toExternalForm());
        Image imgErreurSynt = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/ennemis/erreur_syntaxe_b.png").toExternalForm());

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

        //Barre de vie bind sur la tete de l'ennemi
        ProgressBar barreVie = new ProgressBar();
        barreVie.setId(this.ennemi.getId() + "VIE");

        barreVie.setPrefHeight(10);
        this.paneJeu.getChildren().add(barreVie);
        // divise par la vie max de l'ennemi car la progressBar est defini entre 0 et 1.
        // Cast en double pour utiliser la methode divide(double x) et ainsi une barre de vie cohérente
        barreVie.progressProperty().bind((this.ennemi.pvProperty().divide((double)this.ennemi.getPv())));
        barreVie.translateXProperty().bind(this.ennemi.xProperty().subtract(16));
        barreVie.translateYProperty().bind(this.ennemi.yProperty().subtract(45));

        // Vérification si l'ennemi en question est un boss
        if (this.ennemi.estUnBoss()) {
            differenceX = 3;
            differenceY = 15;
            // Couleur de la barre de vie en rouge pour les boss
            barreVie.setStyle("-fx-accent: red;");
            // La largeur d'un gros boss est de 40 pixel
            barreVie.setPrefWidth(40);
        }

        else {
            // La largeur d'un ennemi est de 32 pixel
            barreVie.setPrefWidth(32);
            //Mettre la couleur de la barre de vie en vert
            barreVie.setStyle("-fx-accent: green;");
        }

        ennemiImgView.setId(ennemi.getId());

        ennemiImgView.translateXProperty().bind(ennemi.xProperty().subtract(Terrain.TAILLE_TUILLE / 2).subtract(differenceX));
        ennemiImgView.translateYProperty().bind(ennemi.yProperty().subtract(Terrain.TAILLE_TUILLE / 2).subtract(differenceY));

        labelArgent.translateXProperty().bind(ennemi.xProperty().subtract(7));
        labelArgent.translateYProperty().bind(ennemi.yProperty().subtract(30));

        // Pas d'index, sinon le boss passe par dessus les bâtiments.
        paneJeu.getChildren().add(ennemiImgView);
        paneJeu.getChildren().add(1, labelArgent);

        labelArgent.setTextFill(Color.DARKGOLDENROD);
        labelArgent.setId(this.ennemi.getId() + "A");
        labelArgent.setVisible(false);

    }
}
