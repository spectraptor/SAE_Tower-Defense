package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import universite_paris8.iut.mcheema.codesource.Main;
import universite_paris8.iut.mcheema.codesource.vue.SonVue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * La classe ControleurAccueil représente le contrôleur du menu d'accueil.
 * Elle gère les interactions de l'utilisateur jusqu'à la sélection d'un niveau.
 * Elle s'occupe également des paramètres, et des options.
 */
public class ControleurAccueil implements Initializable {

    private SonVue sonVue = new SonVue();

    @FXML
    private AnchorPane root;

    @FXML
    private Pane menuPrincipal;

    @FXML
    private Pane menuNiveau;

    @FXML
    private Pane menuParametres;

    @FXML
    private Pane menuCredits;

    @FXML
    private Slider sliderSon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.lookupAll(".button").forEach(node -> {
            node.setOnMouseEntered(e -> sonVue.jouerClique());
            node.setOnMouseClicked(e -> sonVue.jouerBoutonCliquer());
        });

        sliderSon.valueProperty().addListener((obs, ancien, nouveau) -> {
            this.sonVue.setVolumeGlobal(nouveau.doubleValue() / 100.0);
        });
    }

    @FXML
    public void lancerMenu(ActionEvent actionEvent) {
        this.menuNiveau.setVisible(true);
        this.menuPrincipal.setVisible(false);
    }

    @FXML
    public void lancerCredit(ActionEvent actionEvent) {
        this.menuCredits.setVisible(true);
        this.menuPrincipal.setVisible(false);
    }

    @FXML
    public void retourMenu(ActionEvent actionEvent) {
        this.menuParametres.setVisible(false);
        this.menuCredits.setVisible(false);
        this.menuNiveau.setVisible(false);
        this.menuPrincipal.setVisible(true);
    }

    @FXML
    public void lancerParametres(ActionEvent actionEvent) {
        this.menuParametres.setVisible(true);
        this.menuPrincipal.setVisible(false);
    }

    @FXML
    public void quitter(ActionEvent actionEvent) {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void choisirNiveau(ActionEvent actionEvent) throws IOException {
        Button bouton = (Button) actionEvent.getSource();
        int niveau = Integer.parseInt(bouton.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
        Parent root = fxmlLoader.load();

        Controleur controleur = fxmlLoader.getController();
        controleur.setSonVue(sonVue);
        controleur.chargerNiveau(niveau);

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void jouerSon() {
        this.sonVue.jouerAmeliorer();
    }
}
