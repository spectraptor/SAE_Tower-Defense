package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Base {
    private IntegerProperty pvProperty;

    public Base () {
        this.pvProperty = new SimpleIntegerProperty(100);
    }

    public final void setPv(int pv) {this.pvProperty.setValue(pv);}


    public final int getPv() {return this.pvProperty.getValue();}

    public final IntegerProperty pvProperty() { return this.pvProperty;}


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