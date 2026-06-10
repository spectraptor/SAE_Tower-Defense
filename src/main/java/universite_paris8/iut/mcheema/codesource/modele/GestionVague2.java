package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class GestionVague2 {
    private final static int TEMPS_ATTENTE = 50;
    private ArrayList<Vague> listeVague;
    private Environnement environnement;
    private int numeroVague;

    public GestionVague2(Environnement environnement) {
        this.listeVague = new ArrayList<>();
        this.environnement =  environnement;
        this.numeroVague = 1;
    }

    public void f() {
        this.listeVague.add(new Vague(1,this.environnement,1));
        if(this.environnement.getNbTours() % TEMPS_ATTENTE == 0) {
            this.environnement.getEnnemis().add(this.listeVague.get(this.numeroVague).getListeEnnemisVague().remove(0));
        }
        if(this.environnement.getEnnemis().isEmpty() && this.listeVague.get(this.numeroVague).getListeEnnemisVague().isEmpty()) {
            this.numeroVague++;
        }

    }


}
