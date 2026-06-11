package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.media.AudioClip;

public class SonVue {

    private final AudioClip poser;
    private final AudioClip retirer;
    private final AudioClip explosion;
    private final AudioClip mort;
    private final AudioClip apparition;

    public SonVue() {
        this.poser = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/poser.mp3").toExternalForm());
        this.retirer = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/retirer.mp3").toExternalForm());
        this.explosion = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/explosion.mp3").toExternalForm());
        this.mort = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/mort.mp3").toExternalForm());
        this.apparition = new AudioClip(getClass().getResource("/universite_paris8/iut/mcheema/codesource/sons/apparition.mp3").toExternalForm());

        this.poser.setVolume(0.5);
        this.retirer.setVolume(0.5);
        this.explosion.setVolume(0.25);
        this.mort.setVolume(0.5);
        this.apparition.setVolume(0.5);
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
}