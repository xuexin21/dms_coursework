package com.example.demo.level;

import com.example.demo.audio.*;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelOne;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.EnemyPlane;
import javafx.scene.Scene;

/**
 * The LevelOne class represents the first level of the game.
 * It handles the initialization, spawning of enemy units (planes),
 * the game's progress based on kills, and the game-over and level advancement conditions.
 */
public class LevelOne extends LevelParent {

	/**
	 * The background image for the first level.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	/**
	 * The name of the current level.
	 */
	private static final String CURRENT_LEVEL = "com.example.demo.level.LevelOne";

	/**
	 * The name of the next level to transition to.
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelTwo";

	/**
	 * The total number of enemy units to spawn in level one.
	 */
	private static final int TOTAL_ENEMIES = 5;

	/**
	 * The number of kills required to advance to the next level.
	 */
	private static final int KILLS_TO_ADVANCE = 10;

	/**
	 * The probability that an enemy unit will spawn.
	 */
	private static final double ENEMY_SPAWN_PROBABILITY = .20;

	/**
	 * The initial health of the player for level one.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The level view for the first level, responsible for displaying UI elements.
	 */
	private LevelViewLevelOne levelView;

	/**
	 * Constructs a LevelOne object, initializing the level with the necessary parameters.
	 *
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 * @param music the music to be played during the level
	 * @param sound the sound object for sound effects
	 */
	public LevelOne(double screenHeight, double screenWidth, Music music, Sound sound) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, music, sound);
	}

	/**
	 * Checks if the game is over by verifying if the player is destroyed
	 * or if the player has reached the required kill target to advance to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			levelView.hideLevelOne();
			levelView.hideKillCount();
			replayThisLevel(CURRENT_LEVEL);
		}
		else if (userHasReachedKillTarget()) {
			levelView.hideLevelOne();
			levelView.hideKillCount();
			checkToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes the friendly units (the player) for level one.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units (planes) at random positions for level one.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiates and returns the level view for level one.
	 *
	 * @return the level view for level one
	 */
	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewLevelOne(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
		return levelView;
	}

	/**
	 * Checks if the player has reached the required number of kills to advance to the next level.
	 *
	 * @return true if the player has reached the kill target, false otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Updates the level view by showing the current kill count of the player.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		int currentKillCount = getUser().getNumberOfKills();
		levelView.updateKillCount(currentKillCount);
	}

	/**
	 * Initializes the scene for level one, displaying the level and the kill count.
	 *
	 * @return the initialized scene
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.showKillCount();
		levelView.showLevelOne();
		return scene;
	}

}
