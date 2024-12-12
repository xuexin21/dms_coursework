package com.example.demo.audio;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MusicTest {

    private Music music;

    @BeforeEach
    void setUp() {
        new JFXPanel();  // Initialize JavaFX toolkit

        Platform.runLater(() -> {
            music = new Music();  // Initialize the Music object
        });

        try {
            Thread.sleep(600);  // Delay to allow the Music object initialization to complete
        } catch (InterruptedException e) {
            e.printStackTrace();  // Print stack trace if the sleep is interrupted
        }
    }

    @Test
    void mute() {
        music.mute();  // Mute the music

        // Assert that the music is muted
        assertTrue(music.isMuted(), "Music should be muted.");
    }

    @Test
    void unmute() {
        music.unmute();  // Unmute the music

        // Assert that the music is no longer muted
        assertFalse(music.isMuted(), "Music should not be muted.");
    }
}
