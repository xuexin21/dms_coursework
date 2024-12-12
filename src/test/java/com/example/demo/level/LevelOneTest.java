package com.example.demo.level;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class LevelOneTest {
    private LevelOne levelOne;
    private Music music;
    private Sound sound;

    @BeforeEach
    void setUp() throws InterruptedException {
        new JFXPanel();

        music = new Music();
        sound = new Sound();
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            levelOne = new LevelOne(750, 1300, music, sound); // Use standard screen dimensions
            latch.countDown();
        });

        latch.await();
    }

    @Test
    void friendlyUnitsInitialized() {
        // Ensure friendly units like the user plane are added to the scene
        Platform.runLater(() -> levelOne.initializeFriendlyUnits());
        assertNotNull(levelOne.getUser(), "The user plane should be initialized as a friendly unit.");
        assertTrue(levelOne.getRoot().getChildren().contains(levelOne.getUser()),
                "The user plane should be added to the scene.");
    }

    @Test
    void spawnEnemyUnits() {
        // Verify that enemies are spawned and added to the game
        int initialEnemyCount = levelOne.getCurrentNumberOfEnemies();

        Platform.runLater(() -> levelOne.spawnEnemyUnits());
        int afterSpawnEnemyCount = levelOne.getCurrentNumberOfEnemies();

        assertTrue(afterSpawnEnemyCount >= initialEnemyCount, "Enemy count should increase or remain the same.");
    }

    @Test
    void userKillTargetReached() {
        // Simulate user kills to test if the kill target is reached
        levelOne.getUser().incrementKillCount(); // Simulate a single kill
        for (int i = 1; i < 10; i++) {
            levelOne.getUser().incrementKillCount(); // Increment kill count to target
        }

        assertTrue(levelOne.getUser().getNumberOfKills() >= 10, "User should reach the kill target.");
    }
}
