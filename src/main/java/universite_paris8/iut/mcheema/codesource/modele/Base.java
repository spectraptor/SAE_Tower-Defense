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

    public final IntegerProperty getPvProperty() {return this.pvProperty;}
}

