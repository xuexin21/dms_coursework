package com.example.demo.model;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyPlaneTest {

    private EnemyPlane enemyPlane;

    @BeforeEach
    void setUp() {
        new JFXPanel();

        Platform.runLater(() -> {
            enemyPlane = new EnemyPlane(100, 200);
        });

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void initialHealth() {
        assertEquals(1, enemyPlane.getHealth(), "Enemy plane should have initial health of 1.");
    }

    @Test
    void moveHorizontally() {
        double initialXPosition = enemyPlane.getTranslateX();

        Platform.runLater(enemyPlane::updatePosition);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(enemyPlane.getTranslateX() < initialXPosition, "Enemy plane should move horizontally to the left.");
    }

    @Test
    void fireProjectile() {
        ActiveActorDestructible projectile = enemyPlane.fireProjectile();

        if (Math.random() < 0.02) {
            assertNotNull(projectile, "Enemy plane should fire a projectile if the random chance condition is met.");
        } else {
            assertNull(projectile, "Enemy plane should not fire a projectile if the random chance condition is not met.");
        }
    }

    @Test
    void takeDamage() {
        int initialHealth = enemyPlane.getHealth();

        enemyPlane.takeDamage();
        assertEquals(initialHealth - 1, enemyPlane.getHealth(), "Enemy plane health should decrease by 1 after taking damage.");
    }
}
