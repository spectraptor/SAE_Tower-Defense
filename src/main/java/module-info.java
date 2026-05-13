module universite_paris8.iut.mcheema.codesource {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens universite_paris8.iut.mcheema.codesource to javafx.fxml;
    exports universite_paris8.iut.mcheema.codesource;
}