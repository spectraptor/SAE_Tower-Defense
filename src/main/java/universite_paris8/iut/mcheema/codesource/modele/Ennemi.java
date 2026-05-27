package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.Random;
import java.util.ArrayList;

/*

 */

public abstract class Ennemi {
    private String id;
    private static int idCpt = 0;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private int dx; //Direction de l'ennemi 1 signifie vers la droite -1 vers la gauche
    private int dy;
    private double pv;
    private int vitesse;
    private double argentDonne;
    private Environnement environnement;
    private ArrayList<Sommet> chemin;
    private int indiceChemin;

    public Ennemi(int x, int y, double pv, int vitesse, double argentDonne, Environnement env) {
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
        this.chemin = null;
        this.indiceChemin = 0;
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


    public int getVitesse() {
        return this.vitesse;
    }

    public boolean estVivant() {
        return this.pv>0;
    }

    public void meurt() {
        this.pv = 0;
    }


    public void seDeplace() {
        if (aAtteintDestination()) return;

        Sommet prochaine = this.chemin.get(this.indiceChemin + 1);
        int cibleX = prochaine.getColonne() * Terrain.TAILLE_TUILLE;
        int cibleY = prochaine.getLigne() * Terrain.TAILLE_TUILLE;

        int diffX = cibleX - this.getX();
        int diffY = cibleY - this.getY();
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);

        if (distance <= this.vitesse) {
            // Assez proche on se pose sur la tuile et on passe à la suivante
            this.setX(cibleX);
            this.setY(cibleY);
            this.indiceChemin++;
        } else {
            // On avance vers la tuile suivante
            this.setX(this.getX() + (int)(diffX / distance * this.vitesse));
            this.setY(this.getY() + (int)(diffY / distance * this.vitesse));
        }
    }

    public void setChemin(ArrayList<Sommet> chemin) {
        this.chemin = chemin;
        this.indiceChemin = 0;
        if (chemin != null && !chemin.isEmpty()) {
            Sommet depart = chemin.get(0);
            this.setX(depart.getColonne() * Terrain.TAILLE_TUILLE);
            this.setY(depart.getLigne() * Terrain.TAILLE_TUILLE);
        }
    }

    public boolean aAtteintDestination() {
        return this.chemin == null || this.indiceChemin >= this.chemin.size() - 1;
    }


    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public abstract void effectueAction();

    public String toString() {
        return "ID de l'ennemi : " + this.id +
                "\nPV de l'ennemi : " + this.pv +
                "\nPosition de l'ennemi : " + this.getX() + ";" + this.getY();
    }
}
