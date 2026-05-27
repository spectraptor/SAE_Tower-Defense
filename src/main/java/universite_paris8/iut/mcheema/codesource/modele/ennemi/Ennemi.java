package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;

import java.util.Random;

/*

 */

public abstract class Ennemi {
    public final static int PORTEE_ATTAQUE = 10; // Définit la portée de l'attaque sur la base pour chaque ennemi elle sera similaire
    private String id;
    private static int idCpt = 0;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private int dx; //Direction de l'ennemi 1 signifie vers la droite -1 vers la gauche
    private int dy;
    private int pv;
    private int vitesse;
    private int argentDonne;
    private Environnement environnement;

    public Ennemi(int x, int y, int pv, int vitesse, int argentDonne, Environnement env) {
        idCpt++;
        this.id = "E" + idCpt;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.dx = 0;
        this.dy = 0;
        this.pv = pv;
        this.vitesse = vitesse;
        this.argentDonne = argentDonne;
        this.environnement = env;
    }


    public String getId() {
        return this.id;
    }

    public final int getX() {
        return this.xProperty.getValue();
    }

    public final void setX(int x) {
        this.xProperty.setValue(x);
    }

    public final IntegerProperty xProperty() {
        return this.xProperty;
    }

    public final int getY() {
        return this.yProperty.getValue();
    }

    public final void setY(int y) {
        this.yProperty.setValue(y);
    }

    public final IntegerProperty yProperty() {
        return this.yProperty;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public boolean estCamoufle() {
        return false;
    }

    public void attribuerDirectionAleatoire() {
        Random rand = new Random();
        int nDX = rand.nextInt(3) - 1;
        int nDY = rand.nextInt(3) - 1;
        while (nDY == 0 && nDX == 0) {
            nDX = rand.nextInt(3) - 1;
            nDY = rand.nextInt(3) - 1;
        }

        this.dx = nDX;
        this.dy = nDY;
    }

    public int getPv() {
        return this.pv;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public boolean estVivant() {
        return this.pv>0;
    }

    public void meurt() {
        this.pv = 0;
    }

    public void faireDegat(int degat) {
        if (this.pv - degat < 0)
            this.meurt();
        else
            this.pv -= degat;
    }

    public boolean estDansLaPortee() {
        return Math.abs(this.getX()-this.getEnvironnement().getBase().getX())<=PORTEE_ATTAQUE && Math.abs(this.getY()-this.getEnvironnement().getBase().getY())<=PORTEE_ATTAQUE;
    }


    /**
     * Cette méthode permet le déplacement d'un ennemi,
     * elle calcule le prochain mouvement et l'effectue uniquement si le prochain
     * mouvement est dans le terrain va ensuite vérifier si le déplacement suivant est sur le chemin
     */
    public void seDeplace() {
        int nouvX = this.getX() + (this.dx * this.vitesse);
        int nouvY = this.getY() + (this.dy * this.vitesse);
        // Vérifie si la prochaine position est dans le terrain (TilePane)
        if(this.environnement.getTerrainDeJeu().estDansTerrain(nouvX,nouvY)) {
            // Vérifie si la prochaine position est sur le chemin
            if(this.environnement.tuileEstAccessibleCoords(nouvX,nouvY)) {
                this.setX(nouvX);
                this.setY(nouvY);
            }
        }
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }


    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * Cette méthode va effectuer l'action de base d'un ennemi qui est de se déplacer
     * et de vérifier s'il peut attaquer la base.
     * Elle sera réécrite pour chaque ennemi possédant des actions supplémentaires.
     */
    public void effectueAction() {
        this.seDeplace();
        if(this.estDansLaPortee()) {
            this.getEnvironnement().getBase().subirDegats(this.getPv());
            this.meurt();
        }
    }

    public String toString() {
        return "ID de l'ennemi : " + this.id +
                "\nPV de l'ennemi : " + this.pv +
                "\nPosition de l'ennemi : " + this.getX() + ";" + this.getY();
    }
}
