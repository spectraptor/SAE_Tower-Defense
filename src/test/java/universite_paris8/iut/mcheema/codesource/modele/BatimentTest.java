package universite_paris8.iut.mcheema.codesource.modele;
import universite_paris8.iut.mcheema.codesource.modele.batiment.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.ChevalDeTroie;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.DroneEspion;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ping;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BatimentTest {

    private ArrayList<Point> chemin;

    private BombeLogique bombeLogique;
    private Cloud cloud;
    private Debugger debugger;
    private RAM ram;

    private Batiment[] batiments;

    private Environnement environnement;

    @BeforeEach()
    void initTruc() {

        this.chemin = new ArrayList<>();
        this.chemin.add(new Point(1,1));

        this.environnement = new Environnement(1);

        this.bombeLogique = new BombeLogique(0, 0, this.environnement);
        this.cloud = new Cloud(0, 0, this.environnement);
        this.debugger = new Debugger(0, 0, this.environnement);
        this.ram = new RAM(0, 0, this.environnement);
        this.batiments = new Batiment[]{this.bombeLogique, this.cloud, this.debugger, this.ram};
    }

    @Test
    void incrementerNiveau() {
        for (Batiment b : this.batiments) {
            int ancienNiv = b.getNiveau();

            b.incrementerNiveau();

            assertEquals(ancienNiv + 1, b.getNiveau(), "cas incrémenter niveau");
        }
    }

    @Test
    void coutAmelioration() {

        for(Batiment b : batiments){

            int ancien = b.coutProchaineAmelioration();

            b.incrementerNiveau();

            assertTrue(
                    b.coutProchaineAmelioration() > ancien
            );
        }
    }

    @Test
    void cloudPeutAttaquer() {
        Ping ping = new Ping(this.environnement, this.chemin);
        ChevalDeTroie chevalDeTroie = new ChevalDeTroie(this.environnement, this.chemin);

        assertTrue(this.cloud.peutAttaquer(ping), "cas ennemi non camouflé");
        assertFalse(this.cloud.peutAttaquer(chevalDeTroie), "cas ennemi camouflé");
    }


    @Test
    void BombeLogiquePeutAttaquer() {
        Ping ping = new Ping(this.environnement, this.chemin);
        DroneEspion droneEspion = new DroneEspion(this.environnement, this.chemin);
        ChevalDeTroie chevalDeTroie = new ChevalDeTroie(this.environnement, this.chemin);

        assertTrue(this.bombeLogique.peutAttaquer(ping), "cas ennemi non camouflé");
        assertFalse(this.bombeLogique.peutAttaquer(droneEspion), "cas ennemi volant");
        assertFalse(this.cloud.peutAttaquer(chevalDeTroie), "cas ennemi camouflé");
    }


    @Test
    void effectueActionCloud_toucheEnnemi() {
        // Cloud tire tous les tours
        this.environnement.setNbTours(0);
        this.cloud.setCadenceTir(1);

        int ancienNbreProjectiles = this.environnement.getProjectiles().size();

        Ping ping = new Ping(this.environnement, this.chemin);
        ping.setX(21);
        ping.setY(23);

        this.environnement.ajouterEnnemi(ping);

        this.cloud.effectueAction();

        assertEquals(ancienNbreProjectiles + 1, this.environnement.getProjectiles().size(), "cas" +
                "cloud attaque sur ennemi dans sa portée");

    }

    @Test
    void effectueActionCloud_touchePasEnnemiSpecal() {
        // Cloud tire tous les tours
        this.environnement.setNbTours(0);
        this.cloud.setCadenceTir(1);

        int ancienNbreProjectiles = this.environnement.getProjectiles().size();

        ChevalDeTroie chevalDeTroie = new ChevalDeTroie(this.environnement, this.chemin);
        chevalDeTroie.setX(21);
        chevalDeTroie.setY(23);

        this.environnement.ajouterEnnemi(chevalDeTroie);

        this.cloud.effectueAction();

        assertEquals(ancienNbreProjectiles, this.environnement.getProjectiles().size(), "cas" +
                "cloud n'attaque pas l'ennemi");

    }

    @Test
    void effectueActionCloud_neTouchePasEnnemi() {
        // Cloud tire tous les tours
        this.environnement.setNbTours(0);
        this.cloud.setCadenceTir(1);

        int ancienNbreProjectiles = this.environnement.getProjectiles().size();

        Ping ping = new Ping(this.environnement, this.chemin);
        ping.setX(51);
        ping.setY(50);

        this.environnement.ajouterEnnemi(ping);

        this.cloud.effectueAction();

        assertEquals(ancienNbreProjectiles, this.environnement.getProjectiles().size(), "cas" +
                "cloud n'attaque pas l'ennemi");
    }

    @Test
    void effectueActionCloud_unSeulProjectile() {

        cloud.setCadenceTir(1);
        environnement.setNbTours(0);

        Ping proche = new Ping(environnement, chemin);
        proche.setX(21);
        proche.setY(23);

        Ping autre = new Ping(environnement, chemin);
        autre.setX(25);
        autre.setY(25);

        environnement.ajouterEnnemi(proche);
        environnement.ajouterEnnemi(autre);

        cloud.effectueAction();

        assertEquals(1,
                environnement.getProjectiles().size(),
                "un seul ennemi doit être ciblé");
    }

    @Test
    void effectueActionCloud_aucunEnnemi() {

        cloud.setCadenceTir(1);
        environnement.setNbTours(0);

        int avant = environnement.getProjectiles().size();

        cloud.effectueAction();

        assertEquals(avant,
                environnement.getProjectiles().size(),
                "aucun ennemi = aucun tir");
    }

    @Test
    void effectueActionRAM() {
        this.environnement.setNbTours(400);
        int ancienArgent = this.environnement.getArgent();
        this.ram.effectueAction();
        assertEquals(ancienArgent + this.ram.getArgentDonne(), this.environnement.getArgent(), "cas argent génerée par bat ram");
    }
}