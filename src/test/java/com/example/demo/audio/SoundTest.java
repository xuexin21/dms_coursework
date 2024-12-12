package com.example.demo.audio;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoundTest {

    private Sound sound;

    @BeforeEach
    void setUp() {
        new JFXPanel();  // Initialize JavaFX toolkit

        Platform.runLater(() -> {
            sound = new Sound();  // Initialize the Sound object
        });

        try {
            Thread.sleep(600);  // Delay to allow the Sound object initialization to complete
        } catch (InterruptedException e) {
            e.printStackTrace();  // Print stack trace if the sleep is interrupted
        }
    }

    @Test
    void mute() {
        sound.mute();  // Mute the sound

        // Assert that the sound is muted
        assertTrue(sound.isMuted(), "Sound should be muted.");
    }

    @Test
    void unmute() {
        sound.unmute();  // Unmute the sound

        // Assert that the sound is no longer muted
        assertFalse(sound.isMuted(), "Sound should not be muted.");
    }
}
