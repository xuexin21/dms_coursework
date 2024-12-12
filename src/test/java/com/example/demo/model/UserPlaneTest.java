package com.example.demo.model;

import com.example.demo.projectile.UserProjectile;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserPlaneTest {

    private UserPlane userPlane;


    @BeforeEach
    void setUp() {
        new JFXPanel();  // Initialize JavaFX toolkit

        Platform.runLater(() -> {
            // Initialize the Butterfly and UserPlane objects
            userPlane = new UserPlane(5); // Starting with 5 health
        });

        try {
            Thread.sleep(200);  // Allow JavaFX thread to initialize the objects
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void increaseSpeed() {
        // Initially speed is 10
        int initialVerticalSpeed = userPlane.getVerticalVelocity();
        int initialHorizontalSpeed = userPlane.getHorizontalVelocity();

        // Call increase speed
        userPlane.increaseSpeed();

        // Speed should increase by 2, so now it should be 12
        assertEquals(initialVerticalSpeed + 2, userPlane.getVerticalVelocity());
        assertEquals(initialHorizontalSpeed + 2, userPlane.getHorizontalVelocity());
    }

    @Test
    void giveExtraLife() {
        int initialHealth = userPlane.getHealth();

        // Call give extra life
        userPlane.giveExtraLife();

        // Health should increase by 1, but it should not exceed 5
        assertTrue(userPlane.getHealth() <= 5);
    }

    @Test
    void increaseProjectileSpeed() {
        // Assuming UserProjectile has a static method to increase speed, we can simulate that change
        userPlane.increaseProjectileSpeed();

        // Add your checks here based on how increaseProjectileSpeed is affecting UserProjectile
        // Example:
        assertTrue(UserProjectile.getProjectileSpeed() > 1);
    }
}
