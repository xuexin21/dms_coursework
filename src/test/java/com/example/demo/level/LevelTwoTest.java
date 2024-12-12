package com.example.demo.level;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import com.example.demo.model.Boss;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTwoTest {

    private LevelTwo levelTwo;
    private Music music;
    private Sound sound;

    @BeforeEach
    void setUp() throws InterruptedException {
        new JFXPanel(); // Initialize JavaFX toolkit

        music = new Music();
        sound = new Sound();
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            levelTwo = new LevelTwo(750, 1300, music, sound); // Create LevelTwo with standard screen size
            latch.countDown();
        });

        latch.await(); // Wait for initialization
    }

    @Test
    void initializeScene() {
        // Ensure the scene is initialized and configured correctly
        Scene scene = levelTwo.initializeScene();

        assertNotNull(scene, "Scene should be initialized.");
        assertEquals(1300, scene.getWidth(), "Scene width should match the screen width.");
        assertEquals(750, scene.getHeight(), "Scene height should match the screen height.");
    }

    @Test
    void checkIfGameOver_UserDestroyed() {
        // Simulate user destruction and verify game over logic
        Platform.runLater(() -> {
            levelTwo.getUser().takeDamage();
            levelTwo.getUser().takeDamage();
            levelTwo.getUser().takeDamage();
            levelTwo.getUser().takeDamage();
            levelTwo.getUser().takeDamage(); // Destroy user

            levelTwo.checkIfGameOver();

            assertTrue(levelTwo.getUser().isDestroyed(), "User should be destroyed.");
        });
    }

    @Test
    void checkIfGameOver_BossDestroyed() {
        // Simulate boss destruction and verify level advancement logic
        Platform.runLater(() -> {
            levelTwo.spawnEnemyUnits();
            Boss boss = (Boss) levelTwo.getRoot().getChildren()
                    .stream()
                    .filter(node -> node instanceof Boss)
                    .findFirst()
                    .orElse(null);

            assertNotNull(boss, "Boss should be spawned.");
            for (int i = 0; i < 100; i++) boss.takeDamage(); // Destroy boss

            levelTwo.checkIfGameOver();

            assertTrue(boss.isDestroyed(), "Boss should be destroyed.");
        });
    }
}
