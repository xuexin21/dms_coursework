package com.example.demo.controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private Main main;
    private Stage stage;

    @BeforeEach
    void setUp() {
        new JFXPanel();

        Platform.runLater(() -> {
            main = new Main();
            stage = new Stage();
            try {
                main.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    void mainMenuTitle() throws Exception {
        // Set up JavaFX application thread for testing
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                main.start(stage);
                // Verify that the title was set correctly
                assertEquals("Sky Battle", stage.getTitle());
            } catch (Exception e) {
                fail("Exception during start method execution: " + e.getMessage());
            }
        });
    }

    @Test
    void mainMenuShow() {
        Platform.runLater(() -> {
            try {
                assertTrue(stage.isShowing());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
