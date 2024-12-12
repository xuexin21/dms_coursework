package com.example.demo.model;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {

    private Obstacle obstacle;

    @BeforeEach
    void setUp() throws InterruptedException {
        new JFXPanel();  // Initialize JavaFX toolkit

        // Use Platform.runLater to initialize the obstacle object on the JavaFX application thread.
        Platform.runLater(() -> {
            obstacle = new Obstacle(500, 200); // Set the initial position of the obstacle
        });

        try {
            // Allow time for the JavaFX thread to complete its initialization.
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updatePosition() {
        double initialXPosition = obstacle.getTranslateX();

        // Simulate position update
        Platform.runLater(() -> obstacle.updatePosition());

        try {
            Thread.sleep(100);  // Allow time for the position update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The obstacle should have moved to the left by HORIZONTAL_VELOCITY
        assertTrue(obstacle.getTranslateX() < initialXPosition, "Obstacle should move horizontally left.");
    }

    @Test
    void takeDamage() {
        // Since obstacles are indestructible, taking damage shouldn't affect them
        boolean wasDestroyed = obstacle.isDestroyed();
        obstacle.takeDamage();
        assertFalse(wasDestroyed, "Obstacle should not be destroyed when taking damage.");
    }
}
