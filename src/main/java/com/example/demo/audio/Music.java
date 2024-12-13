package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The Music class handles the playback of background music and main menu music
 * for the game. It allows playing, pausing, and stopping music, as well as
 * mute and unmute the sound.
 *
 * It supports playing background music and main menu music on repeat (indefinitely)
 * and provides volume control.
 */
public class Music {

    /**
     * The path to the background music file.
     */
    private static final String BACKGROUND_MUSIC = "/com/example/demo/sounds/backgroundmusic.mp3";

    /**
     * The path to the main menu music file.
     */
    private static final String MAIN_MENU_MUSIC = "/com/example/demo/sounds/main_menu.mp3";

    /**
     * The Media object for the background music.
     * This music is played when the player starts playing.
     */
    private Media background;

    /**
     * The Media object for the main menu music.
     * This music is played when it is at main menu.
     */
    private Media mainMenu;

    /**
     * The MediaPlayer for the background music.
     * This object is used to play the music when the player is playing.
     */
    private MediaPlayer backgroundMusic;

    /**
     * The MediaPlayer for the main menu music.
     * This object is used to play the music when the player at main menu.
     */
    private MediaPlayer mainMenuMusic;

    /**
     * A flag indicating whether the music is muted.
     */
    private boolean isMuted = false;

    /**
     * Constructs a new Music object, initializing the media files and
     * creating media players for both background music and main menu music.
     */
    public Music() {
        background = new Media(getClass().getResource(BACKGROUND_MUSIC).toExternalForm());
        mainMenu = new Media(getClass().getResource(MAIN_MENU_MUSIC).toExternalForm());
        backgroundMusic = new MediaPlayer(background);
        mainMenuMusic = new MediaPlayer(mainMenu);
    }

    /**
     * Plays the main menu music if it is not muted. The music will loop indefinitely
     * and play at full volume.
     */
    public void playMainMenuMusic() {
        if (isMuted) return;
        mainMenuMusic.play();
        mainMenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        mainMenuMusic.setVolume(1.0);
    }

    /**
     * Plays the background music if it is not muted. The music will loop indefinitely
     * and play at half volume.
     */
    public void playBackgroundMusic() {
        if (isMuted) return;
        backgroundMusic.play();
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.setVolume(0.5);
    }

    /**
     * Stops the background music if it is not muted.
     */
    public void stopBackgroundMusic() {
        if (isMuted) return;
        backgroundMusic.stop();
    }

    /**
     * Pauses the background music if it is not muted.
     */
    public void pauseBackgroundMusic() {
        if (isMuted) return;
        backgroundMusic.pause();
    }

    /**
     * Stops the main menu music.
     */
    public void stopMainMenuMusic() {
        mainMenuMusic.stop();
    }

    /**
     * Returns whether the music is currently muted.
     *
     * @return true if the music is muted, false otherwise.
     */
    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Mute the music by setting the isMuted flag to true and pausing the main menu music.
     */
    public void mute() {
        isMuted = true;
        mainMenuMusic.pause();
    }

    /**
     * Unmute the music by setting the isMuted flag to false and resuming the main menu music.
     */
    public void unmute() {
        isMuted = false;
        mainMenuMusic.play();
    }
}
