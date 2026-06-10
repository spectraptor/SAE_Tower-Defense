package universite_paris8.iut.mcheema.codesource.modele;

import com.sun.source.tree.NewArrayTree;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Bogue;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class GestionVague3 {
    private final static int TEMPS_ATTENTE = 100;
    public final static int MAX_VAGUE = 1;
    public ArrayList<Vague> listeVague;
    private Environnement environnement;
    private int vagueCourante;
    private int niveau;

    public GestionVague3(Environnement environnement,int niveau) {
        this.environnement = environnement;
        this.listeVague = new ArrayList<>();
        this.vagueCourante = 0;
        this.niveau = niveau;
        this.listeVague.add(new Vague(0,this.environnement,niveau));
        this.initialiseVague();
    }

    public void metAJour() {
        if(this.environnement.getNbTours() % TEMPS_ATTENTE == 0) {
            if(this.vagueCourante <= this.listeVague.size()) {
                if (!this.listeVague.get(vagueCourante).getListeEnnemisVague().isEmpty())
                    this.environnement.getEnnemis().add(this.listeVague.get(vagueCourante).getListeEnnemisVague().remove(0));
            }
        }
    }

    public ArrayList<Vague> getListeVague() {
        return this.listeVague;
    }

    public void incrementeVagueCourante(){
        this.vagueCourante++;
    }

    public int getVagueCourante() {
        return this.vagueCourante;
    }


    public void initialiseVague() {

    }


}
