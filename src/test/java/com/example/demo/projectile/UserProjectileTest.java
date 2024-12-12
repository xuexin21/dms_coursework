package com.example.demo.projectile;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProjectileTest {

    private UserProjectile userProjectile;

    @BeforeEach
    void setUp() throws InterruptedException {
        new JFXPanel();  // Initialize JavaFX toolkit

        // Initialize the UserProjectile object on the JavaFX application thread
        Platform.runLater(() -> {
            userProjectile = new UserProjectile(800, 200);  // Set initial position for the projectile
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
        double initialXPosition = userProjectile.getTranslateX();

        // Simulate the position update by calling updatePosition
        Platform.runLater(() -> userProjectile.updatePosition());

        try {
            Thread.sleep(100);  // Allow time for the position update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // The projectile should have moved right due to the initial HORIZONTAL_VELOCITY
        assertTrue(userProjectile.getTranslateX() > initialXPosition, "User projectile should move horizontally right.");
    }

    @Test
    void increaseProjectileVelocity() {
        // Save the initial velocity
        int initialVelocity = UserProjectile.getProjectileSpeed();

        // Increase the velocity
        UserProjectile.increaseProjectileVelocity();

        // Check that the velocity has increased but does not exceed 30
        assertTrue(UserProjectile.getProjectileSpeed() > initialVelocity, "Projectile speed should increase after calling increaseProjectileVelocity.");
        assertTrue(UserProjectile.getProjectileSpeed() <= 30, "Projectile speed should not exceed 30.");
    }

    @Test
    void resetProjectileVelocity() {
        // Increase the projectile velocity first
        UserProjectile.increaseProjectileVelocity();
        int increasedVelocity = UserProjectile.getProjectileSpeed();

        // Reset the velocity
        UserProjectile.resetProjectileVelocity();

        // Check that the velocity is reset to the initial value (15)
        assertEquals(15, UserProjectile.getProjectileSpeed(), "Projectile speed should reset to 15.");
        assertTrue(increasedVelocity > 15, "Projectile speed should have been increased before resetting.");
    }

    @Test
    void updateActor() {
        // Capture initial position
        double initialXPosition = userProjectile.getTranslateX();

        // Update the actor (calls updatePosition internally)
        Platform.runLater(() -> userProjectile.updateActor());

        try {
            Thread.sleep(100);  // Allow time for the update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the projectile has moved after the update
        assertTrue(userProjectile.getTranslateX() > initialXPosition, "User projectile should move after updateActor() is called.");
    }
}
