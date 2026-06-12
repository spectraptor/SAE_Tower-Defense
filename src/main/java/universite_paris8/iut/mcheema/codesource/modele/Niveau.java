package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

public class Niveau {

    private ArrayList<Point> bases;
    private ArrayList<Point> entrees;

    public Niveau(int numero) {
        this.bases = new ArrayList<>();
        this.entrees = new ArrayList<>();
        creer(numero);
    }
    public void creer(int numero) {

        switch (numero) {
            case 1:
                this.bases.add(new Point(0, 12));
                this.entrees.add(new Point(19, 4));
                break;

            case 2:
                this.bases.add(new Point(0, 7));
                this.entrees.add(new Point(15, 0));
                this.bases.add(new Point(0, 11));
                this.entrees.add(new Point(13, 14));
                break;

            default:
                this.bases.add(new Point(9, 7));
                this.entrees.add(new Point(19, 7));
                break;
        }

    }

    public ArrayList<Point> getBases() {
        return bases;
    }

    public ArrayList<Point> getEntrees() {
        return entrees;
    }

    public int getNbreEntres() {
        return this.entrees.size();
    }
}