package com.example.demo.model;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ButterflyTest {

    private Butterfly butterfly;
    private UserPlane user;

    @BeforeEach
    void setUp() {
        new JFXPanel();  // Initialize JavaFX toolkit

        Platform.runLater(() -> {
            // Initialize the Butterfly and UserPlane objects
            user = new UserPlane(3);  // Set initial health for the user
            butterfly = new Butterfly(500, 200, 1);  // Set initial position for Butterfly
        });

        try {
            Thread.sleep(200);  // Allow JavaFX thread to initialize the objects
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void initialHealth() {
        assertEquals(1, butterfly.getHealth(), "Butterfly should have initial health of 1.");
    }

    @Test
    void updatePosition() {
        double initialXPosition = butterfly.getTranslateX();

        // Simulate position update
        Platform.runLater(butterfly::updatePosition);

        try {
            Thread.sleep(100);  // Allow time for the position update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The butterfly should have moved to the left (because HORIZONTAL_VELOCITY is negative)
        assertTrue(butterfly.getTranslateX() < initialXPosition, "Butterfly should move horizontally left.");
    }
}
