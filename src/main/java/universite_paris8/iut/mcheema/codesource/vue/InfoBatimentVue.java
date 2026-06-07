package universite_paris8.iut.mcheema.codesource.vue;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentTir;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

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
            textInfo.setPrefHeight(HAUTEUR_PANE - HAUTEUR_PANE /4);
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

            VBox boiteBoutonAction = new VBox();
            boiteBoutonAction.setPrefWidth(LARGEUR_PANE);
            boiteBoutonAction.setPrefHeight(HAUTEUR_PANE/4);
            boiteBoutonAction.setTranslateX(0);
            boiteBoutonAction.setTranslateY(HAUTEUR_PANE-HAUTEUR_PANE/4);
            paneInfo.getChildren().add(boiteBoutonAction);

            HBox boiteVenDep = new HBox();
            boiteVenDep.setPrefWidth(boiteBoutonAction.getPrefWidth());
            boiteVenDep.setPrefHeight(boiteBoutonAction.getPrefHeight());
            boiteBoutonAction.getChildren().add(boiteVenDep);

            Button boutonAmeliorer = new Button("Ameliorer");
            boutonAmeliorer.setPrefWidth(LARGEUR_PANE);
            boutonAmeliorer.setPrefHeight(boiteBoutonAction.getPrefHeight() - boiteVenDep.getPrefHeight());
            boiteBoutonAction.getChildren().add(boutonAmeliorer);

            boutonAmeliorer.setOnAction(e -> {
                    this.batiment.ameliorerBatiment();
                    this.paneJeu.getChildren().remove(paneInfo);
            });

            Button boutonVendre = new Button("Vendre");
            boutonVendre.setPrefWidth(LARGEUR_PANE /2);
            boutonVendre.setPrefHeight(HAUTEUR_PANE / 4);
            boiteVenDep.getChildren().add(boutonVendre);

            boutonVendre.setOnAction(event -> {
                this.batiment.vendreBatiment();
                this.paneJeu.getChildren().remove(paneInfo);
            });

            Button boutonDeplacer = new Button("Deplacer");
            boutonDeplacer.setPrefWidth(LARGEUR_PANE /2);
            boutonDeplacer.setPrefHeight(HAUTEUR_PANE / 4);
            boiteVenDep.getChildren().add(boutonDeplacer);


            boutonDeplacer.setOnAction(event -> {
                this.paneJeu.getChildren().remove(paneInfo);
                Pane paneTemp = new Pane();
                paneTemp.setPrefWidth(this.paneJeu.getPrefWidth());
                paneTemp.setPrefHeight(this.paneJeu.getPrefHeight());
                paneTemp.setId(this.batiment.getId()+"D");
                this.paneJeu.getChildren().add(paneTemp);
                paneTemp.setOnMouseClicked(e -> {
                    this.batiment.deplacerBatiment2((int)e.getX(),(int)e.getY());
                    this.paneJeu.getChildren().remove(paneTemp);
                });
            });




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
