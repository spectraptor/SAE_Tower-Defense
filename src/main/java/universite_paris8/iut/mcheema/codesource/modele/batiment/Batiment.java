package universite_paris8.iut.mcheema.codesource.modele.batiment;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

/**
 * La classe Batiment s'occupe de la gestion de tours et de leur logique.
 * Elle s'occupe de leurs comportements, de leur déplacements, améliorations...
 */

public abstract class Batiment {
    private static int idCpt = 0;
    private String id;
    private String nom;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private int prix;
    private int niveau;
    private Environnement environnement;

    public Batiment(String nom,int x, int y,int prix, Environnement env) {
        idCpt++;
        this.id = "B" + idCpt;
        this.nom = nom;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.prix = prix;
        this.niveau = 1;
        this.environnement = env;
    }

    public String getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public final IntegerProperty xProperty() {
        return this.xProperty;
    }

    public final IntegerProperty yProperty() {
        return this.yProperty;
    }

    public final int getX() {
        return this.xProperty.getValue();
    }

    public final int getY() {
        return this.yProperty.getValue();
    }

    public final void setX(int x) {
        this.xProperty.set(x);
    }

    public final void setY(int y) {
        this.yProperty.set(y);
    }

    public int getPrix() {
        return this.prix;
    }

    public int getNiveau() {
        return this.niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int avoirPrixVente() {
        return this.prix / 4;
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

            if(!this.environnement.partieEstFinie()) {
                if(this.environnement.tuileTourPosable(nouvX,nouvY) && !this.environnement.tuileContientUnBatiment(centreTuileX,centreTuileY)) {
                    this.setX(centreTuileX);
                    this.setY(centreTuileY);
                    this.getEnvironnement().setArgent((int) (this.getEnvironnement().getArgent() - (this.getPrix() /10) * distanceNbTuile));
                    System.out.println((this.getPrix() /10) * distanceNbTuile);
                }
            }
        }
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }


    public abstract void effectueAction();

    public abstract void ameliorerBatiment();

    /**
     * Calcul la distance entre un batiment et un ennemi
     * @param ennemi l'ennemi dont on souhaite connaitre la distance par rapport au batiment
     * @return la distance euclidienne entre le batiment et l'ennemi
     */
    public double calculDistance(Ennemi ennemi) {
        return  (Math.sqrt((this.getX() - ennemi.getX()) * (this.getX() - ennemi.getX()) + (this.getY() - ennemi.getY()) * (this.getY() - ennemi.getY())));
    }

    public double calculDistance(Batiment batiment) {
        return  (Math.sqrt((this.getX() - batiment.getX()) * (this.getX() - batiment.getX()) + (this.getY() - batiment.getY()) * (this.getY() - batiment.getY())));
    }

    public int coutProchaineAmelioration() {
        return (this.getNiveau()+1) *(this.getPrix() /4);
    }



    public String toString() {
        return  "      " + this.getNom() +  "      ";
    }


}
