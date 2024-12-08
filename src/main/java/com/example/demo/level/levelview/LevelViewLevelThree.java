package com.example.demo.level.levelview;

import com.example.demo.view.KillCountDisplay;
import com.example.demo.view.PowerUp;
import javafx.scene.Group;

public class LevelViewLevelThree extends LevelView {

	private final Group root;
	private final KillCountDisplay killCountDisplay;
	private final PowerUp powerUp;
	private int lastDroppedKillCount; // Track the last kill count at which a power-up was dropped

	public LevelViewLevelThree(Group root, int heartsToDisplay, int killsToAdvance) {
		super(root, heartsToDisplay);
		this.root = root;
		this.killCountDisplay = new KillCountDisplay(killsToAdvance);
		this.powerUp = new PowerUp();
		this.lastDroppedKillCount = 0; // Initialize to 0
	}

	public void showKillCount() {
		root.getChildren().add(killCountDisplay);
	}

	public void updateKillCount(int killCount) {
		killCountDisplay.updateKillCount(killCount);
	}

	public void displayPowerUp() {
		root.getChildren().add(powerUp);
	}

	public void dropPowerUp(int killCount) {
		if (killCount % 10 == 0 && killCount != 0) {
			positionPowerup(killCount);
			powerUp.showPowerUp();
		}
	}

	public void positionPowerup(int killCount) {
		if (killCount > lastDroppedKillCount) {
			// Define the bounds for the power-up's position
			lastDroppedKillCount = killCount; // Update the last dropped kill count
			double minX = 100; // Minimum x position
			double maxX = 500; // Maximum x position
			double minY = 100; // Minimum y position
			double maxY = 500; // Maximum y position

			// Generate random positions within the defined bounds
			double randomX = minX + Math.random() * (maxX - minX);
			double randomY = minY + Math.random() * (maxY - minY);
			powerUp.setLayout(randomX, randomY);
		}
	}
}
