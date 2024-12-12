package com.example.demo.level;

import com.example.demo.audio.*;
import com.example.demo.model.Boss;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelTwo;
import javafx.scene.Scene;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelThree";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private Sound sound;
	private static final int bossHealth = 100;
	private LevelViewLevelTwo levelView;
	private boolean shieldActivated = false;

	public LevelTwo(double screenHeight, double screenWidth, Music music, Sound sound) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, music, sound);
		boss = new Boss(bossHealth);
		this.sound = sound;
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
			levelView.hideLevelTwo();
			levelView.hideBossHealth();
		}
		else if (boss.isDestroyed()) {
			levelView.hideLevelTwo();
			levelView.hideBossHealth();
			goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH, bossHealth);
		return levelView;
	}

	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		levelView.displayShield();
		levelView.showLevelTwo();
		levelView.showBossHealth();
		return scene;
	}

	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		// update shield position
		levelView.updateShieldPosition(boss);

		if (boss.isShielded()) {
			if (!shieldActivated) sound.playShieldSound();
			shieldActivated = true;
			levelView.showShield();
		} else {
			if (shieldActivated) sound.playNoShieldSound();
			shieldActivated = false;
			levelView.hideShield();
		}

		// update boss health
		levelView.updateBossHealth(boss.getHealth());
		levelView.updateHealthDisplayPosition(boss);
	}
}
