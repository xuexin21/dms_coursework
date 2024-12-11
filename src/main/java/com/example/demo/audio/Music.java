package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
    private static final String BACKGROUND_MUSIC = "/com/example/demo/sounds/backgroundmusic.mp3";
    private static final String MAIN_MENU = "/com/example/demo/sounds/main_menu.mp3";
    private Media background;
    private Media mainMenu;
    private MediaPlayer backgroundMusic;
    private MediaPlayer mainMenuMusic;
    private boolean isMuted = false;

    public Music() {
        background = new Media(getClass().getResource(BACKGROUND_MUSIC).toExternalForm());
        mainMenu = new Media(getClass().getResource(MAIN_MENU).toExternalForm());
        backgroundMusic = new MediaPlayer(background);
        mainMenuMusic = new MediaPlayer(mainMenu);
    }

    public void playMainMenuMusic() {
        if (isMuted) return;
        mainMenuMusic.play();
        mainMenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        mainMenuMusic.setVolume(20);
    }

    public void playBackgroundMusic() {
        if (isMuted) return;
        backgroundMusic.play();
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.setVolume(30);
    }

    public void stopBackgroundMusic() {
        if (isMuted) return;
        backgroundMusic.stop();
    }

    public void pauseBackgroundMusic() {
        if (isMuted) return;
        backgroundMusic.pause();
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
