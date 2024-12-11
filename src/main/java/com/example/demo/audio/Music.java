package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
    private static final String MAIN_MENU = "/com/example/demo/sounds/main_menu.mp3";
    private Media mainMenu;
    private MediaPlayer mainMenuMusic;
    private boolean isMuted = false;

    public Music() {
        mainMenu = new Media(getClass().getResource(MAIN_MENU).toExternalForm());
        mainMenuMusic = new MediaPlayer(mainMenu);
    }

    public void playMainMenuMusic() {
        if (isMuted) return;
        mainMenuMusic.play();
        mainMenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        mainMenuMusic.setVolume(20);
    }

    public void stopMainMenuMusic() {
        mainMenuMusic.stop();
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void mute() {
        isMuted = true;
        mainMenuMusic.pause();
    }

    public void unmute() {
        isMuted = false;
        mainMenuMusic.play();
    }
}
