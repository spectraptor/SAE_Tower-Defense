package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Entite;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
/**
 * La classe Batiment s'occupe de la gestion de tours et de leur logique.
 * Elle s'occupe de leurs comportements, de leur déplacements, améliorations...
 */

public abstract class Batiment extends Entite {
    private static int idCpt = 0;
    private String nom;
    private int prix;
    private int niveau;
    private int niveauMax;

    // Réduction de 10 %
    public static final double REDUCTION_NIVEAU = 0.10;

    public Batiment(String nom, double x, double y, int prix, int nivMax, Environnement env) {
        super("B" + idCpt++, x, y, env);
        this.nom = nom;
        this.prix = prix;
        this.niveau = 1;
        this.niveauMax = nivMax;
    }

    public int getPrix() {
        return this.prix;
    }

    public int getNiveau() {
        return this.niveau;
    }

    public String getNom() {
        return this.nom;
    }

    public int getNiveauMax() {
        return this.niveauMax;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
  
    public int avoirPrixVente() {
        return this.niveau * this.prix / 4;
    }

    public void vendreBatiment( ) {
        this.getEnvironnement().getBatiments().remove(this);
        this.getEnvironnement().setArgent(this.getEnvironnement().getArgent() + this.avoirPrixVente());
    }

    public void acheterBatiment() {
        if(this.getEnvironnement().getArgent() >= this.getPrix()) {
            this.getEnvironnement().ajouterBatiment(this);
            this.getEnvironnement().setArgent(this.getEnvironnement().getArgent() - this.getPrix());
        }
    }

    public void deplacerBatiment2(int nouvX,int nouvY) {
        if(this.getEnvironnement().getArgent() >= this.getPrix() / 2) {
          
            int[] lignesColonnesTuile = this.environnement.getTerrainDeJeu().convertirCoordsTuile(nouvX, nouvY);
            int centreTuileX = lignesColonnesTuile[1] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
            int centreTuileY = lignesColonnesTuile[0] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
            int distanceNbTuile = (Math.abs(this.getX() - centreTuileX) + Math.abs(this.getY() - centreTuileY))/Terrain.TAILLE_TUILLE;

            if(!this.getEnvironnement().partieEstFinie()) {
                if(this.getEnvironnement().tuileTourPosable(nouvX,nouvY) && !this.getEnvironnement().tuileContientUnBatiment(centreTuileX,centreTuileY)) {
                    this.setX(centreTuileX);
                    this.setY(centreTuileY);
                    this.getEnvironnement().setArgent((int) (this.getEnvironnement().getArgent() - (this.getPrix() /10) * distanceNbTuile));
                    System.out.println((this.getPrix() /10) * distanceNbTuile);
                }
            }
        }
    }

    public void incrementerNiveau() {
        this.setNiveau(this.getNiveau() + 1);
    }

    public abstract void ameliorerBatiment();

    public int coutProchaineAmelioration() {
        return (this.getNiveau()+1) *(this.getPrix() / 4);
    }

    /**
     * Permet de calculer le pourcentage d'amelioration, ou de reduction qu'il faut accorder à une caractéristique d'un bâtiment après
     * son amélioration.
     *  Elle dépend de REDUCTION_NIVEAU (10%, pour l'instant), qui donne le pourcentage qu'il faut appliquer à chaque niveau.
     *  Ex :
     *  Niv 1. 10% -> 0.1
     *  Niv 2. 20% -> 0.2
     *
     * @return le pourcentage nécessaire au calcul suite à l'amélioration d'un bâtiment.
     */
    public double pourcentageReduction() {
        return this.getNiveau() * REDUCTION_NIVEAU;
    }

    public String toString() {
        return  "      " + this.getNom() +  "      ";
    }


}
