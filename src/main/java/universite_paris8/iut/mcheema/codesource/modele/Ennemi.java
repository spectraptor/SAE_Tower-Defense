package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

import java.util.Random;

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
        if(this.environnement.estDedans(this.getX() + (this.dx * this.vitesse),this.getY() + (this.dy * this.vitesse))) {
            if(this.environnement.tuileEstAccessible(this)) {
                this.setX(this.getX() + (this.dx * this.vitesse));
                this.setY(this.getY() + (this.dy * this.vitesse));
            }
        }
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
