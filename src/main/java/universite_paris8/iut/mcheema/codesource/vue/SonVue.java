package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.media.AudioClip;

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


    public SonVue() {
        this.poser = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/poser.mp3").toExternalForm());
        this.retirer = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/retirer.mp3").toExternalForm());
        this.explosion = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/explosion.mp3").toExternalForm());
        this.mort = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/mort.mp3").toExternalForm());
        this.apparition = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/apparition.mp3").toExternalForm());
        this.missileTete = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/tir1.mp3").toExternalForm());
        this.missileZone = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/tir2.mp3").toExternalForm());
        this.missileFragmentation = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/tir3.mp3").toExternalForm());
        this.clique = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/clique.mp3").toExternalForm());


        this.poser.setVolume(0.5);
        this.retirer.setVolume(0.5);
        this.explosion.setVolume(0.25);
        this.mort.setVolume(0.5);
        this.apparition.setVolume(0.3);
        this.missileTete.setVolume(0.3);
        this.missileZone.setVolume(0.3);
        this.missileFragmentation.setVolume(0.5);
        this.clique.setVolume(0.3);

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
}