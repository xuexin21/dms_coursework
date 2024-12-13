package com.example.demo.level;

import com.example.demo.audio.*;
import com.example.demo.model.Boss;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelTwo;
import javafx.scene.Scene;

/**
 * This class represents Level Two in the game. It includes the boss fight mechanics, spawning the boss,
 * managing the player's health, and updating the UI elements related to the boss's health and shield status.
 */
public class LevelTwo extends LevelParent {

	/**
	 * The path to the background image resource for the level.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

	/**
	 * The class name representing the current level in the game (Level Two).
	 */
	private static final String CURRENT_LEVEL = "com.example.demo.level.LevelTwo";

	/**
	 * The class name representing the next level in the game (Level Three).
	 */
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelThree";

	/**
	 * The initial health of the player when starting the level.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The boss object for the current level, representing the game's boss character.
	 */
	private final Boss boss;

	/**
	 * The Sound object that handles sound effects or background music in the game.
	 */
	private Sound sound;

	/**
	 * The initial health of the boss for this level.
	 */
	private static final int bossHealth = 50;

	/**
	 * The view object representing the display or UI for Level Two.
	 */
	private LevelViewLevelTwo levelView;

	/**
	 * A flag indicating whether the player's shield is currently activated.
	 */
	private boolean shieldActivated = false;


	/**
	 * Constructs a new LevelTwo object with the specified screen height, screen width, music, and sound.
	 * Additionally, it initializes the boss with a predefined health value.
	 *
	 * @param screenHeight The height of the screen for this level.
	 * @param screenWidth  The width of the screen for this level.
	 * @param music        The music to be played during the level.
	 * @param sound        The sound effects to be used during the level.
	 */
	public LevelTwo(double screenHeight, double screenWidth, Music music, Sound sound) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, music, sound);
		boss = new Boss(bossHealth);
		this.sound = sound;
	}

	/**
	 * Initializes the friendly units for the level by adding the user plane to the game.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over, either by the player's destruction or by defeating the boss.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			levelView.hideLevelTwo();
			levelView.hideBossHealth();
			replayThisLevel(CURRENT_LEVEL);
		} else if (boss.isDestroyed()) {
			levelView.hideLevelTwo();
			levelView.hideBossHealth();
			checkToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Spawns enemy units into the game. In this level, the only enemy unit is the boss,
	 * and it is spawned when no other enemies are present.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates the LevelView for Level Two, which handles the user interface elements specific to this level,
	 * such as the boss health and shield.
	 *
	 * @return The instantiated LevelView for this level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH, bossHealth);
		return levelView;
	}

	/**
	 * Initializes the scene for Level Two, displaying the level background, boss health, and shield UI elements.
	 *
	 * @return The scene for this level.
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.displayShield();
		levelView.showLevelTwo();
		levelView.showBossHealth();
		return scene;
	}

	/**
	 * Updates the level view each frame, including updating the shield status, boss health, and UI positions.
	 * If the boss is shielded, the shield sound is played, and if the shield is disabled, a sound is triggered.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		levelView.updateShieldPosition(boss);

		if (boss.isShielded()) {
			if (!shieldActivated) {
				sound.playShieldSound();
				shieldActivated = true;
			}
			levelView.showShield();
		} else {
			if (shieldActivated) {
				sound.playNoShieldSound();
				shieldActivated = false;
			}
			levelView.hideShield();
		}

		levelView.updateBossHealth(boss.getHealth());
		levelView.updateHealthDisplayPosition(boss);
	}
}
