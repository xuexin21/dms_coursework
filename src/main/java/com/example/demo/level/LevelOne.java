package com.example.demo.level;

import com.example.demo.audio.*;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelOne;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.EnemyPlane;
import javafx.scene.Scene;

public class LevelOne extends LevelParent {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String CURRENT_LEVEL = "com.example.demo.level.LevelOne";
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private LevelViewLevelOne levelView;

	public LevelOne(double screenHeight, double screenWidth, Music music, Sound sound) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, music, sound);
	}

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
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		this.levelView = new LevelViewLevelOne(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
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
		levelView.showLevelOne();
		return scene;
	}

}
