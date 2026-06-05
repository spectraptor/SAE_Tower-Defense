package universite_paris8.iut.mcheema.codesource.vue;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;

public class InfoBatimentVue {
    private final static int LARGEUR_PANE = 200;
    private final static int HAUTEUR_PANE = 200;
    private Batiment batiment;
    private Pane paneJeu;

    public InfoBatimentVue(Batiment batiment, Pane paneJeu) {
        this.batiment = batiment;
        this.paneJeu = paneJeu;
    }

    public void afficheInfoBatiment() {
        if(this.paneJeu.lookup("#"+this.batiment.getId() + "I") == null) {
            Pane paneInfo = new Pane();
            paneInfo.setId(this.batiment.getId() + "I");
            paneInfo.setPrefWidth(LARGEUR_PANE);
            paneInfo.setPrefHeight(HAUTEUR_PANE);

            Label textInfo = new Label();
            textInfo.setPrefWidth(LARGEUR_PANE);
            textInfo.setPrefHeight(HAUTEUR_PANE);
            textInfo.setId("textInfoBatiment");
            textInfo.setText(this.batiment.toString());
            textInfo.setPadding(new Insets(5, 0, 5, 15));
            paneInfo.getChildren().add(textInfo);

            Button boutonQuitter = new Button("X");
            boutonQuitter.setPrefWidth(35);
            boutonQuitter.setPrefHeight(35);
            boutonQuitter.setTranslateX(LARGEUR_PANE - boutonQuitter.getPrefWidth());
            boutonQuitter.setTranslateY(0);
            paneInfo.getChildren().add(boutonQuitter);
            boutonQuitter.setId("boutonQuitterInfoBat");

            HBox boiteAction = new HBox();
            boiteAction.setPrefWidth(LARGEUR_PANE);
            boiteAction.setPrefHeight(HAUTEUR_PANE / 4);
            boiteAction.setTranslateX(0);
            boiteAction.setTranslateY(HAUTEUR_PANE-boiteAction.getPrefHeight());
            paneInfo.getChildren().add(boiteAction);

            Button boutonVendre = new Button("Vendre");
            boutonVendre.setPrefWidth(LARGEUR_PANE /2);
            boutonVendre.setPrefHeight(HAUTEUR_PANE / 4);
            boiteAction.getChildren().add(boutonVendre);

            Button boutonDeplacer = new Button("Deplacer");
            boutonDeplacer.setPrefWidth(LARGEUR_PANE /2);
            boutonDeplacer.setPrefHeight(HAUTEUR_PANE / 4);

            boiteAction.getChildren().add(boutonDeplacer);


            this.paneJeu.getChildren().add(4, paneInfo);

            if (this.batiment.getX() <= this.paneJeu.getPrefWidth() / 2) {
                paneInfo.setTranslateX(batiment.getX());
            } else {
                paneInfo.setTranslateX(batiment.getX() - paneInfo.getPrefWidth());
            }
            if (this.batiment.getY() <= this.paneJeu.getPrefHeight() / 2) {
                paneInfo.setTranslateY(batiment.getY());
            } else {
                paneInfo.setTranslateY(batiment.getY() - paneInfo.getPrefHeight());
            }
            boutonQuitter.setOnAction(event -> {
                this.paneJeu.getChildren().remove(paneInfo);
            });
        }
    }

}
