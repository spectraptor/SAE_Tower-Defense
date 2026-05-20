package universite_paris8.iut.mcheema.codesource;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.mcheema.codesource.controleur.Controleur;

import javax.swing.*;
import java.io.IOException;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
        Parent root = fxmlLoader.load();

        // Obtenir le controleur : on en a besoin pour avoir les touches du clavier
        Controleur controleur = fxmlLoader.getController();
        Scene scene = new Scene(root, 900, 480);

        // Indique d'appeler la méthode deplacerEnnemi du controleur
        // scene.setOnKeyPressed(controleur::deplacerEnnemi);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}