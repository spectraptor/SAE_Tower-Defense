package universite_paris8.iut.mcheema.codesource;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.mcheema.codesource.controleur.Controleur;
import java.io.IOException;

/**
 * Main gère le lancement du jeu.
 */

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}