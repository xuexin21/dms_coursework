package com.example.demo.level;

import com.example.demo.model.Boss;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelTwo;
import javafx.scene.Scene;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelThree";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private static final int bossHealth = 100;
	private LevelViewLevelTwo levelView;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(bossHealth);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
			levelView.hideBossHealth();
		}
		else if (boss.isDestroyed()) {
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
		levelView.showBossHealth();
		return scene;
	}

	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		// update shield position
		levelView.updateShieldPosition(boss);

		if (boss.isShielded()) {
			levelView.showShield();
		} else {
			levelView.hideShield();
		}

		// update boss health
		levelView.updateBossHealth(boss.getHealth());
		levelView.updateHealthDisplayPosition(boss);
	}
}
