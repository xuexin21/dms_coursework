package com.example.demo.projectile;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyProjectileTest {

    private EnemyProjectile enemyProjectile;

    @BeforeEach
    void setUp() throws InterruptedException {
        new JFXPanel();  // Initialize JavaFX toolkit

        // Initialize the EnemyProjectile object on the JavaFX application thread
        Platform.runLater(() -> {
            enemyProjectile = new EnemyProjectile(800, 200);  // Set initial position for the projectile
        });

        try {
            // Allow time for JavaFX thread to initialize the object
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updatePosition() {
        double initialXPosition = enemyProjectile.getTranslateX();

        // Simulate the position update by calling updatePosition
        Platform.runLater(() -> enemyProjectile.updatePosition());

        try {
            Thread.sleep(100);  // Allow time for the position update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The projectile should have moved left due to the HORIZONTAL_VELOCITY
        assertTrue(enemyProjectile.getTranslateX() < initialXPosition, "Enemy projectile should move horizontally left.");
    }

    @Test
    void updateActor() {
        // Capture initial position
        double initialXPosition = enemyProjectile.getTranslateX();

        // Update the actor (calls updatePosition internally)
        Platform.runLater(() -> enemyProjectile.updateActor());

        try {
            Thread.sleep(100);  // Allow time for the update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the projectile has moved after the update
        assertTrue(enemyProjectile.getTranslateX() < initialXPosition, "Enemy projectile should move after updateActor() is called.");
    }
}
