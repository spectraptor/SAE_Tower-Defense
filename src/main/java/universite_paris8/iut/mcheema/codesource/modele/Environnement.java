package universite_paris8.iut.mcheema.codesource.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;
import java.util.ArrayList;
/*
La classe Environnement gère l'ensemble du fonctionnement du jeu. Il effectue les actions à chaque tour,
l'apparition de tous les élements du jeu (base, terrain, liste d'ennemis, liste de batiments.
 */
public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private ArrayList<Batiment> batiments;
    private ObservableList<Projectile> projectiles;
    private int nbreVague;
    private Terrain terrainDeJeu;
    private int nbTours;
    private Base base;


    public Environnement(int niveau, Base base) {
        this.ennemis = FXCollections.observableArrayList();
        this.batiments = new ArrayList<>();
        this.projectiles = FXCollections.observableArrayList();
        this.nbreVague = 0;
        this.terrainDeJeu = new Terrain(niveau);
        this.nbTours = 0;
        this.base = base;
    }

    public Terrain getTerrainDeJeu() {
        return this.terrainDeJeu;
    }

    public ObservableList<Ennemi> getEnnemis() {
        return this.ennemis;
    }

    public ArrayList<Batiment> getBatiments() {
        return this.batiments;
    }

    public ObservableList<Projectile> getProjectiles() {
        return this.projectiles;
    }

    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }


    public void ajouterBatiment(Batiment batiment) {
        this.batiments.add(batiment);
    }

    public void ajouterProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    public Base getBase() {
        return this.base;
    }

    public void unTour() {
        ArrayList<Ennemi> ennemisMort = new ArrayList<>();
        ArrayList<Projectile> projectilesArrive = new ArrayList<>();
        if (!this.partieEstFinie()) {
            for (Ennemi ennemi : this.ennemis) {
                if (this.nbTours % 5 == 0) {
                    if (ennemi.estVivant()) {
                        ennemi.effectueAction();
                    } else {
                        ennemisMort.add(ennemi);
                    }
                }
            }
            for (Batiment batiment : this.batiments) {
                batiment.effectueAction();
            }

            for(Projectile projectile : this.projectiles) {
                projectile.seDeplacer();
                if(projectile.getEstArrive()) {
                    projectilesArrive.add(projectile);
                }
            }

            for (Ennemi ennemi : ennemisMort) {
                ennemi.meurt();
                this.getEnnemis().remove(ennemi);
            }
            for(Projectile projectile : projectilesArrive) {
                this.getProjectiles().remove(projectile);
            }
            this.nbTours++;
        }
    }

    public boolean tuileEstAccessibleCoords(int nouveauX, int nouveauY) {
        return this.terrainDeJeu.tuileEstAccessibleCoords(nouveauX,nouveauY);
    }

    /**
     * Regarde si une tour est déja présente dans une tuile
     * @param x les coordonnées x de la tuile
     * @param y les coordonnées y de la tuile
     * @return true si une tuile est déjà présente, false autrement.
     */
    public boolean tuileContientUnBatiment(int x, int y) {
        boolean sortieBoucle = false;
        int i = 0;
        while (i < this.batiments.size() && !sortieBoucle) {
            if(x == this.batiments.get(i).getX() && y == this.batiments.get(i).getY()) {
                sortieBoucle = true;
            }
            i++;
        }
        return sortieBoucle;
    }

    public boolean partieEstFinie() {
        return this.getEnnemis().isEmpty() || this.getBase().estDetruite();
    }

    public int getNbTours() {
        return this.nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }
}

