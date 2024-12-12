package com.example.demo.projectile;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossProjectileTest {

    private BossProjectile bossProjectile;

    @BeforeEach
    void setUp() throws InterruptedException {
        new JFXPanel();  // Initialize JavaFX toolkit

        // Initialize the BossProjectile object on the JavaFX application thread
        Platform.runLater(() -> {
            bossProjectile = new BossProjectile(200);  // Set initial Y position for the projectile
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
        double initialXPosition = bossProjectile.getTranslateX();

        // Simulate the position update by calling updatePosition
        Platform.runLater(() -> bossProjectile.updatePosition());

        try {
            Thread.sleep(100);  // Allow time for the position update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The projectile should have moved left due to the HORIZONTAL_VELOCITY
        assertTrue(bossProjectile.getTranslateX() < initialXPosition, "Boss projectile should move horizontally left.");
    }

    @Test
    void updateActor() {
        // Capture initial position
        double initialXPosition = bossProjectile.getTranslateX();

        // Update the actor (calls updatePosition internally)
        Platform.runLater(() -> bossProjectile.updateActor());

        try {
            Thread.sleep(100);  // Allow time for the update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the projectile has moved after the update
        assertTrue(bossProjectile.getTranslateX() < initialXPosition, "Boss projectile should move after updateActor() is called.");
    }

}
