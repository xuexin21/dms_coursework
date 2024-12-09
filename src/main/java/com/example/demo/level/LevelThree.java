package com.example.demo.level;

import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelThree;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.Butterfly;
import com.example.demo.model.SecondEnemyPlane;
import javafx.scene.Scene;

public class LevelThree extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.png";
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelFour";
	private static final int TOTAL_ENEMIES = 5;
	private static final int TOTAL_BUTTERFLIES = 2;
	private static final int KILLS_TO_ADVANCE = 30;
	private static final double ENEMY_SPAWN_PROBABILITY = .05;
	private static final double BUTTERFLY_SPAWN_PROBABILITY = .02;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private LevelViewLevelThree levelView;

	public LevelThree(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			winGame();
		}
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

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

	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
		return levelView;
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		int currentKillCount = getUser ().getNumberOfKills();
		levelView.updateKillCount(currentKillCount);
	}

	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.showKillCount();
		return scene;
	}
}
