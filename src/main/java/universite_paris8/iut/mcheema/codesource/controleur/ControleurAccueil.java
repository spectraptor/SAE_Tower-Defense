package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import universite_paris8.iut.mcheema.codesource.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Gère tout ce qui se passe dans le menu jusqu'à la sélection d'un niveau
 */

public class ControleurAccueil implements Initializable {

    private final AudioClip interactionBruit = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/clique.mp3").toExternalForm());

    @FXML
    private AnchorPane root;

    @FXML
    private Pane menuPrincipal;

    @FXML
    private Pane menuNiveau;

    @FXML
    private Pane menuCredits;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        interactionBruit.setVolume(0.3);
        root.lookupAll(".button").forEach(node -> {node.setOnMouseEntered(e -> interactionBruit.play());});
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
        this.menuCredits.setVisible(false);
        this.menuNiveau.setVisible(false);
        this.menuPrincipal.setVisible(true);
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
        controleur.chargerNiveau(niveau);

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
