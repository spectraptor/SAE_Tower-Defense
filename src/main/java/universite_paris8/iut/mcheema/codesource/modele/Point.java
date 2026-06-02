package universite_paris8.iut.mcheema.codesource.modele;
/**
 * La classe Tuile connait sa position en ligne et en colonne.
 * Elle est utilisé pour l'algorithme.
 */
public class Point {
    private int ligne;
    private int colonne;

    public Point(int colonne, int ligne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() { return this.ligne; }
    public int getColonne() { return this.colonne;}



    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        return this.ligne == other.ligne && this.colonne == other.colonne;
    }

    public int hashCode() {
        return 31 * this.ligne + this.colonne;
    }

    public String toString() {
        return "Tuile[" + this.ligne + ", " + this.colonne + "]";
    }
}