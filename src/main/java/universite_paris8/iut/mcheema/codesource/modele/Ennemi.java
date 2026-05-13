package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

    public Ennemi(int x,int y,double pv, int vitesse,double argentDonne, Environnement environnement) {
        idCpt++;
        this.id = "E"+ idCpt;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.pv = pv;
        this.vitesse = vitesse;
        this.argentDonne = argentDonne;
        this.environnement = environnement;
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

    public boolean estVivant() {
        return this.pv>0;
    }

    public void meurt() {
        this.pv = 0;
    }

    public void seDeplacer(KeyEvent e) {
        int nPosX, nPosY;
        switch (e.getCode()) {
            case D:
                this.dx = 1;
                nPosX = this.getX() + (this.vitesse * this.dx);
                this.setX(nPosX);
                break;
            case Q:
                this.dx = -1;
                nPosX = this.getX() + (this.vitesse * this.dx);
                this.setX(nPosX);
                break;
            case Z:
                this.dy = -1;
                nPosY = this.getY() + (this.vitesse * this.dy);
                this.setY(nPosY);
                break;
            case S:
                this.dy = 1;
                nPosY = this.getY() + (this.vitesse * this.dy);
                this.setY(nPosY);
                break;
        }

    }
    

    public abstract void effectueAction();

    public String toString() {
        return "ID de l'ennemi : " + this.id +
                "\nPV de l'ennemi : " + this.pv +
                "\nPosition de l'ennemi : " + this.getX() + ";" + this.getY();
    }
}
