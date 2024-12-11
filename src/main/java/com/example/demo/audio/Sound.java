package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private static final String BUTTON_SOUND = "/com/example/demo/sounds/button_sound.mp3";
    private Media button;
    private MediaPlayer buttonSound;
    private boolean isMuted = false;

    public Sound() {
        button = new Media(getClass().getResource(BUTTON_SOUND).toExternalForm());
        buttonSound = new MediaPlayer(button);
    }

    public void playButtonSound() {
        if (isMuted) return;
        buttonSound.stop();
        buttonSound.play();
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void mute() {
        isMuted = true;
        buttonSound.pause();
    }

    public void unmute() {
        isMuted = false;
        buttonSound.play();
    }
}
