package com.example.demo.level;

import com.example.demo.audio.*;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelThree;
import com.example.demo.model.*;
import javafx.scene.Scene;

/**
 * This class represents Level Three in the game. It handles the specific mechanics of this level,
 * such as spawning enemies, butterflies, and obstacles, as well as checking win conditions and managing
 * the player's progress.
 */
public class LevelThree extends LevelParent {

	/**
	 * The path to the background image resource for Level Three.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.png";

	/**
	 * The class name representing the current level in the game (Level Three).
	 */
	private static final String CURRENT_LEVEL = "com.example.demo.level.LevelThree";

	/**
	 * The class name representing the next level in the game (Level Four).
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelFour";

	/**
	 * The total number of enemy units that will appear in the level.
	 */
	private static final int TOTAL_ENEMIES = 5;

	/**
	 * The total number of butterflies that will appear in the level.
	 */
	private static final int TOTAL_BUTTERFLIES = 2;

	/**
	 * The total number of obstacles that will appear in the level.
	 */
	private static final int TOTAL_OBSTACLES = 2;

	/**
	 * The number of kills required by the player to advance to the next level.
	 */
	private static final int KILLS_TO_ADVANCE = 40;

	/**
	 * The probability that an enemy will spawn during each update in the level.
	 */
	private static final double ENEMY_SPAWN_PROBABILITY = .04;

	/**
	 * The probability that a butterfly will spawn during each update in the level.
	 */
	private static final double BUTTERFLY_SPAWN_PROBABILITY = .01;

	/**
	 * The probability that an obstacle will spawn during each update in the level.
	 */
	private static final double OBSTACLE_SPAWN_PROBABILITY = .01;

	/**
	 * The initial health of the player when starting the level.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The view object representing the display or UI for Level Three.
	 */
	private LevelViewLevelThree levelView;


	/**
	 * Constructs a new LevelThree object with the specified screen height, screen width, music, and sound.
	 *
	 * @param screenHeight The height of the screen for this level.
	 * @param screenWidth  The width of the screen for this level.
	 * @param music        The music to be played during the level.
	 * @param sound        The sound effects to be used during the level.
	 */
	public LevelThree(double screenHeight, double screenWidth, Music music, Sound sound) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, music, sound);
	}

	/**
	 * Checks if the game is over, either by the player's destruction or by meeting the kill target.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			levelView.hideLevelThree();
			levelView.hideKillCount();
			getUser().reset();
			replayThisLevel(CURRENT_LEVEL);
		} else if (userHasReachedKillTarget()) {
			levelView.hideLevelThree();
			levelView.hideKillCount();
			getUser().reset();
			checkToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes the friendly units for the level by adding the user plane to the game.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units into the game based on the defined spawn probability.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new SecondEnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Spawns butterfly units into the game based on the defined spawn probability.
	 */
	@Override
	protected void spawnButterflyUnits() {
		for (int i = 0; i < TOTAL_BUTTERFLIES; i++) {
			if (Math.random() < BUTTERFLY_SPAWN_PROBABILITY) {
				double newButterflyInitialYPosition = Math.random() * getButterflyMaximumYPosition();
				ActiveActorDestructible newButterfly = new Butterfly(getScreenWidth(), newButterflyInitialYPosition, 1); // Assuming health is 1
				addButterflyUnit(newButterfly);
			}
		}
	}

	/**
	 * Spawns obstacle units into the game based on the defined spawn probability.
	 */
	@Override
	protected void spawnObstacle() {
		for (int i = 0; i < TOTAL_OBSTACLES; i++) {
			if (Math.random() < OBSTACLE_SPAWN_PROBABILITY) {
				double newObstacleInitialYPosition = Math.random() * getObstacleMaximumYPosition();
				ActiveActorDestructible newObstacle = new Obstacle(getScreenWidth(), newObstacleInitialYPosition); // Assuming health is 1
				addObstacleUnit(newObstacle);
			}
		}
	}

	/**
	 * Instantiates the LevelView for Level Three, which handles the user interface elements for the level.
	 *
	 * @return The instantiated LevelView for this level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
		return levelView;
	}

	/**
	 * Checks if the player has reached the kill target required to advance to the next level.
	 *
	 * @return True if the player has reached the kill target, false otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Updates the level view with the current kill count of the player.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		int currentKillCount = getUser().getNumberOfKills();
		levelView.updateKillCount(currentKillCount);
	}

	/**
	 * Initializes the scene for Level Three, adding the level view and kill count to the scene.
	 *
	 * @return The scene for this level.
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.showLevelThree();
		levelView.showKillCount();
		return scene;
	}
}