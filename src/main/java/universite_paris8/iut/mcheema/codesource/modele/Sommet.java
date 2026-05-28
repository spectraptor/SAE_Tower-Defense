package universite_paris8.iut.mcheema.codesource.modele;

public class Sommet {
    private int ligne;
    private int colonne;

    public Sommet(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() { return this.ligne; }
    public int getColonne() { return this.colonne; }


    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Sommet other = (Sommet) obj;
        return this.ligne == other.ligne && this.colonne == other.colonne;
    }

    public int hashCode() {
        return 31 * this.ligne + this.colonne;
    }

    public String toString() {
        return "Tuile[" + this.ligne + ", " + this.colonne + "]";
    }
}