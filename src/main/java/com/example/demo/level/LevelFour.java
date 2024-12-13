package com.example.demo.level;

import com.example.demo.audio.*;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelFour;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.Boss;
import com.example.demo.model.Butterfly;
import com.example.demo.model.SecondBoss;
import com.example.demo.model.Obstacle;
import javafx.scene.Scene;

/**
 * The LevelFour class represents the fourth level of the game.
 * It handles the initialization, spawning of enemy units (bosses, butterflies, and obstacles),
 * and the game-over and win conditions specific to this level.
 */
public class LevelFour extends LevelParent {

	/**
	 * The background image for the fourth level.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.png";

	/**
	 * The name of the current level.
	 */
	private static final String CURRENT_NAME = "com.example.demo.level.LevelFour";

	/**
	 * The initial health of the player for level four.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The total number of butterflies to spawn in level four.
	 */
	private static final int TOTAL_BUTTERFLIES = 2;

	/**
	 * The total number of obstacles to spawn in level four.
	 */
	private static final int TOTAL_OBSTACLES = 2;

	/**
	 * The probability that an obstacle will spawn.
	 */
	private static final double OBSTACLE_SPAWN_PROBABILITY = .01;

	/**
	 * The probability that a butterfly will spawn.
	 */
	private static final double BUTTERFLY_SPAWN_PROBABILITY = .007;

	/**
	 * The health value of the boss in level four.
	 */
	private static final int bossHealth = 100;

	/**
	 * The first boss of level four.
	 */
	private final Boss firstboss;

	/**
	 * The sound object used to play sound effects in level four.
	 */
	private Sound sound;

	/**
	 * The second boss of level four.
	 */
	private SecondBoss secondBoss;

	/**
	 * Flag indicating if the second boss has been spawned.
	 */
	private boolean secondBossSpawned;

	/**
	 * The level view for the fourth level, responsible for displaying UI elements.
	 */
	private LevelViewLevelFour levelView;

	/**
	 * Flag indicating if the shield is activated for the boss one.
	 */
	private boolean shieldActivatedBossOne = false;

	/**
	 * Flag indicating if the shield is activated for the boss two.
	 */
	private boolean shieldActivatedBossTwo = false;

	/**
	 * Constructs a LevelFour object, initializing the level with the necessary parameters.
	 *
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 * @param music the music to be played during the level
	 * @param sound the sound object for sound effects
	 */
	public LevelFour(double screenHeight, double screenWidth, Music music, Sound sound) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, music, sound);
		this.sound = sound;
		firstboss = new Boss(bossHealth);
		secondBoss = new SecondBoss();
		secondBossSpawned = false;
	}

	/**
	 * Initializes the friendly units (the player) for level four.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over by verifying if the player is destroyed
	 * or if all enemies (bosses) are destroyed.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			levelView.hideLevelFour();
			levelView.firstBossHideBossHealth();
			levelView.secondBossHideBossHealth();
			getUser().reset();
			replayThisLevel(CURRENT_NAME);
		}
		else if (firstboss.isDestroyed() && (secondBossSpawned && secondBoss.isDestroyed())) {
			levelView.firstBossHideBossHealth();
			levelView.secondBossHideBossHealth();
			levelView.hideLevelFour();
			winGame();
		}
	}

	/**
	 * Spawns enemy units (bosses) for level four.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(firstboss);
		}
		if (!secondBossSpawned && firstboss.getHealth() <= bossHealth / 2)  {
			spawnSecondBoss();
			secondBossSpawned = true;
		}
	}

	/**
	 * Spawns butterfly units at random positions for level four.
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
	 * Spawns obstacle units at random positions for level four.
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
	 * Instantiates and returns the level view for level four.
	 *
	 * @return the level view for level four
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelFour(getRoot(), PLAYER_INITIAL_HEALTH, bossHealth);
		return levelView;
	}

	/**
	 * Initializes the scene for level four, displaying the level and boss health.
	 *
	 * @return the initialized scene
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.firstBossDisplayShield();
		levelView.showLevelFour();
		levelView.firstBossShowBossHealth();
		return scene;
	}

	/**
	 * Updates the level view by checking the bosses' shield and health status.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		levelView.firstBossUpdateShieldPosition(firstboss);
		levelView.firstBossUpdateBossHealth(firstboss.getHealth());
		levelView.firstBossUpdateHealthDisplayPosition(firstboss);

		if (firstboss.isShielded()) {
			if (!shieldActivatedBossOne) {
				sound.playShieldSound();
				shieldActivatedBossOne = true;
			}
			levelView.firstBossShowShield();
		}
		else {
			if (shieldActivatedBossOne) {
				sound.playNoShieldSound();
				shieldActivatedBossOne = false;
			}
			levelView.firstBossHideShield();
		}

		if (firstboss.isDestroyed()) levelView.firstBossHideBossHealth();

		levelView.secondBossUpdateShieldPosition(secondBoss);
		levelView.secondBossUpdateBossHealth(secondBoss.getHealth());
		levelView.secondBossUpdateHealthDisplayPosition(secondBoss);

		if (secondBoss.isShielded()) {
			if (!shieldActivatedBossTwo) {
				sound.playShieldSound();
				shieldActivatedBossTwo = true;
			}
			levelView.secondBossShowShield();
		}
		else {
			if (shieldActivatedBossTwo) {
				sound.playNoShieldSound();
				shieldActivatedBossTwo = false;
			}
			levelView.secondBossHideShield();
		}

		if(secondBoss.isDestroyed()) levelView.secondBossHideBossHealth();
	}

	/**
	 * Spawns the second boss and shows the associated shield and health displays.
	 */
	private void spawnSecondBoss() {
		addEnemyUnit(secondBoss);
		levelView.secondBossDisplayShield();
		levelView.secondBossShowBossHealth();
	}
}
