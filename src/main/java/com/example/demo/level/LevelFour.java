package com.example.demo.level;

import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelFour;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.Butterfly;
import com.example.demo.model.Boss;
import javafx.scene.Scene;

public class LevelFour extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.png";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final double BUTTERFLY_SPAWN_PROBABILITY = .02;
	private static final int TOTAL_BUTTERFLIES = 2;
	private final Boss boss;
	private static final int bossHealth = 100;
	private LevelViewLevelFour levelView;

	public LevelFour(double screenHeight, double screenWidth) {
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
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
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
		levelView = new LevelViewLevelFour(getRoot(), PLAYER_INITIAL_HEALTH, bossHealth);
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
