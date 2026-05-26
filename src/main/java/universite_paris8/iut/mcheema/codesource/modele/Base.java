package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Base {
    private IntegerProperty pvProperty;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;

    public Base (int x, int y) {
        this.pvProperty = new SimpleIntegerProperty(100);
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
    }

    public final void setPv(int pv) {this.pvProperty.setValue(pv);}


    public final int getPv() {return this.pvProperty.getValue();}

    public final IntegerProperty pvProperty() { return this.pvProperty;}

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


    public void subirDegats(int degats) {
        if(this.getPv()-degats < 0) {
            this.setPv(0);
        }
        else {
            this.setPv(this.getPv() - degats);
        }
    }

    public boolean estDetruite() {
        return getPv() <= 0;
    }
}