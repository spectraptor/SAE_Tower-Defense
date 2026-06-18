package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.media.AudioClip;

/**
 * La classe Sonvue détient tous les sons du jeu.
 * Elle permet ainsi de jouer certains sons qu'on voudrait utilisées à des moments précis.
 */
public class SonVue {

    private final AudioClip poser;
    private final AudioClip retirer;
    private final AudioClip explosion;
    private final AudioClip mort;
    private final AudioClip apparition;
    private final AudioClip missileTete;
    private final AudioClip missileZone;
    private final AudioClip missileFragmentation;
    private final AudioClip clique;
    private final AudioClip deplacer;
    private final AudioClip ameliorer;
    private final AudioClip poserConfirmer;
    private final AudioClip boutonCliquer;

    public SonVue() {
        this.poser = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/interface/i_poser.mp3").toExternalForm());
        this.retirer = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/interface/i_retirer.mp3").toExternalForm());
        this.explosion = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/batiment/b_explosion.mp3").toExternalForm());
        this.mort = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/ennemi/e_mort.mp3").toExternalForm());
        this.apparition = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/ennemi/e_apparition.mp3").toExternalForm());
        this.missileTete = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/batiment/b_tir1.mp3").toExternalForm());
        this.missileZone = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/batiment/b_tir2.mp3").toExternalForm());
        this.missileFragmentation = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/batiment/b_tir3.mp3").toExternalForm());
        this.clique = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/interface/i_clique.mp3").toExternalForm());
        this.deplacer = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/interface/i_deplacer.mp3").toExternalForm());
        this.ameliorer = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/interface/i_ameliorer.mp3").toExternalForm());
        this.poserConfirmer = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/interface/i_poserConfirmer.mp3").toExternalForm());
        this.boutonCliquer = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/interface/i_boutonCliquer.mp3").toExternalForm());
        this.setVolumeGlobal(0.5);

    }

    public void setVolumeGlobal(double volume) {
        this.poser.setVolume(volume);
        this.retirer.setVolume(volume);
        this.explosion.setVolume(volume);
        this.mort.setVolume(volume);
        this.apparition.setVolume(volume);
        this.missileTete.setVolume(volume);
        this.missileZone.setVolume(volume);
        this.missileFragmentation.setVolume(volume);
        this.clique.setVolume(volume);
        this.deplacer.setVolume(volume);
        this.ameliorer.setVolume(volume);
        this.poserConfirmer.setVolume(volume);
        this.boutonCliquer.setVolume(volume);
    }

    public void jouerPoser() {
        this.poser.play();
    }

    public void jouerRetirer() {
        this.retirer.play();
    }

    public void jouerExplosion() {
        this.explosion.play();
    }

    public void jouerMort() {
        this.mort.play();
    }

    public void jouerApparition() {
        this.apparition.play();
    }

    public void jouerMissileTete() {
        this.missileTete.play();
    }

    public void jouerMissileZone() {
        this.missileZone.play();
    }

    public void jouerMissileFragmentation() {
        this.missileFragmentation.play();
    }

    public void jouerClique() {
        this.clique.play();
    }

    public void jouerDeplacer() {
        this.deplacer.play();
    }

    public void jouerAmeliorer() {
        this.ameliorer.play();
    }

    public void jouerPoserConfirmer() {
        this.poserConfirmer.play();
    }

    public void jouerBoutonCliquer() {
        this.boutonCliquer.play();
    }
}