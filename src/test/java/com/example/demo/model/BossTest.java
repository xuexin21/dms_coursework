package com.example.demo.model;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossTest {

    private Boss boss;

    @BeforeEach
    void setUp() {
        new JFXPanel();  // Initialize JavaFX toolkit

        // Initialize the Boss object on the JavaFX application thread.
        Platform.runLater(() -> {
            boss = new Boss(100); // Create a Boss object with initial health of 100
        });

        try {
            // Allow time for the JavaFX thread to complete its initialization.
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void takeDamageWhenNotShielded() {
        int initialHealth = boss.getHealth(); // Capture the initial health of the Boss

        // Simulate taking damage
        boss.takeDamage();

        // Assert that the health decreases by 1 when the Boss is not shielded
        assertEquals(initialHealth - 1, boss.getHealth(), "Boss health should decrease by 1 when not shielded.");
    }

    @Test
    void healthAtZero() {
        // Simulate the Boss taking damage 100 times, reducing health to zero
        for (int i = 0; i < 100; i++) {
            boss.takeDamage();
        }

        // Assert that the Boss is destroyed when its health reaches zero
        assertTrue(boss.isDestroyed(), "Boss should be destroyed when health reaches zero.");
    }
}
