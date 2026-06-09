package universite_paris8.iut.mcheema.codesource.vue;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
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
            //Pane dans lequel il y'a les informations, boutons (achat,vente,deplacement)
            Pane paneInfo = new Pane();
            paneInfo.setId(this.batiment.getId() + "I");
            paneInfo.setPrefWidth(LARGEUR_PANE);
            paneInfo.setPrefHeight(HAUTEUR_PANE);

            //Label qui affiche les informations
            Label textInfo = new Label();
            textInfo.setPrefWidth(LARGEUR_PANE);
            textInfo.setPrefHeight(HAUTEUR_PANE - HAUTEUR_PANE /4);
            textInfo.setId("textInfoBatiment");
            textInfo.setText(this.batiment.toString());
            textInfo.setPadding(new Insets(5, 0, 5, 15));
            paneInfo.getChildren().add(textInfo);

            //Bouton qui permet de fermer le paneInfo
            Button boutonQuitter = new Button("X");
            boutonQuitter.setPrefWidth(35);
            boutonQuitter.setPrefHeight(35);
            boutonQuitter.setTranslateX(LARGEUR_PANE - boutonQuitter.getPrefWidth());
            boutonQuitter.setTranslateY(0);
            paneInfo.getChildren().add(boutonQuitter);
            boutonQuitter.setId("boutonQuitterInfoBat");

            boutonQuitter.setOnAction(event -> {
                this.paneJeu.getChildren().remove(paneInfo);
            });

            //VBox qui contient les 3 boutons ( vente,déplacement et amélioration)
            VBox boiteBoutonAction = new VBox();
            boiteBoutonAction.setPrefWidth(LARGEUR_PANE);
            boiteBoutonAction.setPrefHeight(HAUTEUR_PANE/4);
            boiteBoutonAction.setTranslateX(0);
            boiteBoutonAction.setTranslateY(HAUTEUR_PANE-HAUTEUR_PANE/4);
            paneInfo.getChildren().add(boiteBoutonAction);

            //HBox qui contient les boutons vente et déplacement
            HBox boiteVenDep = new HBox();
            boiteVenDep.setPrefWidth(boiteBoutonAction.getPrefWidth());
            boiteVenDep.setPrefHeight(boiteBoutonAction.getPrefHeight());
            boiteBoutonAction.getChildren().add(boiteVenDep);

            //Bouton situé tout en bas du PaneInfo pour l'amelioration du batiment
            Button boutonAmeliorer = new Button("Ameliorer");
            boutonAmeliorer.setPrefWidth(LARGEUR_PANE);
            boutonAmeliorer.setPrefHeight(boiteBoutonAction.getPrefHeight() - boiteVenDep.getPrefHeight());
            boiteBoutonAction.getChildren().add(boutonAmeliorer);

            //Si le batiment a atteint son niveau max le bouton devient inaccessible
            if(this.batiment.getNiveau() == 2) {
                boutonAmeliorer.setDisable(true);
            }

            boutonAmeliorer.setOnMouseEntered(e-> {
                boutonAmeliorer.setText(Integer.toString(this.batiment.coutProchaineAmelioration()) + " OCTET");
            });
            boutonAmeliorer.setOnMouseExited(e-> {
                boutonAmeliorer.setText("Ameliorer");
            });

            boutonAmeliorer.setOnAction(e -> {
                this.batiment.ameliorerBatiment();
                this.paneJeu.getChildren().remove(paneInfo);
            });


            //Bouton vendre
            Button boutonVendre = new Button("Vendre");
            boutonVendre.setPrefWidth(LARGEUR_PANE /2);
            boutonVendre.setPrefHeight(HAUTEUR_PANE / 4);
            boiteVenDep.getChildren().add(boutonVendre);

            boutonVendre.setOnMouseEntered(e-> {
                boutonVendre.setText("+"+Integer.toString(this.batiment.avoirPrixVente()));
            });
            boutonVendre.setOnMouseExited(e-> {
                boutonVendre.setText("Vendre");
            });
            boutonVendre.setOnAction(event -> {
                this.batiment.vendreBatiment();
                this.paneJeu.getChildren().remove(paneInfo);
            });


            Button boutonDeplacer = new Button("Deplacer");
            boutonDeplacer.setPrefWidth(LARGEUR_PANE /2);
            boutonDeplacer.setPrefHeight(HAUTEUR_PANE / 4);
            boiteVenDep.getChildren().add(boutonDeplacer);

            boutonDeplacer.setOnAction(event -> {
                //Enleve le PaneInfo lorsqu'on clique sur le bouton déplacer
                this.paneJeu.getChildren().remove(paneInfo);
                //Crée un Pane temporaire pour le clique du déplacement du batiment ce Pane a exactement les memes dimension que le Pane de Jeu
                Pane paneTemp = new Pane();
                paneTemp.setPrefWidth(this.paneJeu.getPrefWidth());
                paneTemp.setPrefHeight(this.paneJeu.getPrefHeight());
                paneTemp.setId(this.batiment.getId()+"D");
                this.paneJeu.getChildren().add(paneTemp);

                //Label qui affiche le cout du déplacement du batiment le cout est un dixieme du prix du batiment multiplié par le nombre de tuile déplacé
                Label labelCoutDep = new Label();
                labelCoutDep.setPrefWidth(220);
                labelCoutDep.setPrefHeight(50);
                labelCoutDep.setDisable(true);
                labelCoutDep.setTranslateX(this.paneJeu.getPrefWidth() - labelCoutDep.getPrefWidth());
                labelCoutDep.setTranslateY(this.paneJeu.getPrefHeight() - labelCoutDep.getPrefHeight());
                labelCoutDep.setId("labelCoutDepBat");
                this.paneJeu.getChildren().add(labelCoutDep);

                //Crée une ImageView temporaire pour avoir un apercu du nouvelle emplacement du batiment
                ImageView imgVBat = (ImageView) this.paneJeu.lookup("#" + this.batiment.getId());
                ImageView nouvImgVBat = new ImageView(imgVBat.getImage());
                nouvImgVBat.setDisable(true);
                nouvImgVBat.setId(this.batiment.getId() + "DEP");
                this.paneJeu.getChildren().add(nouvImgVBat);

                //Permet d'avoir des coordonnées sans passer par le pane de jeu sinon le joueur pourra déplacer a l'infini le batiment
                paneTemp.setOnMouseClicked(e -> {
                    this.batiment.deplacerBatiment2((int)e.getX(),(int)e.getY());
                    this.paneJeu.getChildren().remove(paneTemp);
                    this.paneJeu.getChildren().remove(nouvImgVBat);
                    this.paneJeu.getChildren().remove(labelCoutDep);
                });

                //Permet d'afficher la nouvelle position semi-transparente du déplacement du batiment et également le cout du déplacement
                paneTemp.setOnMouseMoved(e-> {
                    //Enleve l'image si la tuile actuelle n'est pas utilisable par le batiment
                    if(this.batiment.getEnvironnement().tuileContientUnBatiment((int)e.getX(),(int)e.getY()) || !this.batiment.getEnvironnement().tuileTourPosable((int)e.getX(),(int)e.getY())) {
                        this.paneJeu.getChildren().remove(nouvImgVBat);
                    }

                    else {
                        //Affiche l'image si la tuile est une tuile posable pour le batiment, verifie avant l'ajout si il n'existe pas déja dans le Pane principal
                        if(this.paneJeu.lookup("#"+this.batiment.getId()+"DEP") == null) {
                            this.paneJeu.getChildren().add(nouvImgVBat);
                        }
                    }

                    //Gere la semi-transparence de l'image et force le positionnement du batiment au centre de la tuile.
                    nouvImgVBat.setOpacity(0.5);
                    int[] lignesColonnesTuile = this.batiment.getEnvironnement().getTerrainDeJeu().convertirCoordsTuile((int)e.getX(), (int)e.getY());
                    int centreTuileX = lignesColonnesTuile[1] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
                    int centreTuileY = lignesColonnesTuile[0] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
                    int distance = (int)(Math.abs(centreTuileX - this.batiment.getX()) + Math.abs(centreTuileY - this.batiment.getY())) / Terrain.TAILLE_TUILLE;
                    labelCoutDep.setText("  Prix du déplacement : " + Integer.toString(this.batiment.getPrix()/10 * distance));

                    //Force l'affichage de l'image au centre de la tuile pour avoir un apercu conforme au placement du batiment
                    nouvImgVBat.setTranslateX((centreTuileX - Terrain.TAILLE_TUILLE/2));
                    nouvImgVBat.setTranslateY((centreTuileY - Terrain.TAILLE_TUILLE/2));
                });
            });




            //Permet de faire en sorte que le PaneInfo e sorte pas de l'écran, s'adapte à la ou le batiment se situe
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
        }
    }

}
