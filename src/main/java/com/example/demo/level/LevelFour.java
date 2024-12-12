package com.example.demo.level;

import com.example.demo.audio.*;
import com.example.demo.level.levelview.LevelView;
import com.example.demo.level.levelview.LevelViewLevelFour;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.Butterfly;
import com.example.demo.model.Boss;
import com.example.demo.model.SecondBoss;
import com.example.demo.model.Obstacle;
import javafx.scene.Scene;

public class LevelFour extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.png";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final int TOTAL_BUTTERFLIES = 2;
	private	static final int TOTAL_OBSTACLES = 1;
	private static final double OBSTACLE_SPAWN_PROBABILITY = .01;
	private static final double BUTTERFLY_SPAWN_PROBABILITY = .01;
	private static final int bossHealth = 100;
	private final Boss firstboss;
	private SecondBoss secondBoss;
	private boolean secondBossSpawned;
	private LevelViewLevelFour levelView;

	public LevelFour(double screenHeight, double screenWidth, Music music, Sound sound) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, music, sound);
		firstboss = new Boss(bossHealth);
		secondBoss = new SecondBoss();
		secondBossSpawned = false;
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
			levelView.firstBossHideBossHealth();
			levelView.secondBossHideBossHealth();
		}
		else if (firstboss.isDestroyed() && (secondBossSpawned && secondBoss.isDestroyed())) {
			levelView.firstBossHideBossHealth();
			levelView.secondBossHideBossHealth();
			winGame();
		}
	}

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
	protected void spawnObstacle() {
		for (int i = 0; i < TOTAL_OBSTACLES; i++) {
			if (Math.random() < OBSTACLE_SPAWN_PROBABILITY) {
				double newObstacleInitialYPosition = Math.random() * getObstacleMaximumYPosition();
				ActiveActorDestructible newObstacle = new Obstacle(getScreenWidth(), newObstacleInitialYPosition); // Assuming health is 1
				addObstacleUnit(newObstacle);
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
		levelView.firstBossDisplayShield();
		levelView.firstBossShowBossHealth();
		return scene;
	}

	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		levelView.firstBossUpdateShieldPosition(firstboss);
		levelView.firstBossUpdateBossHealth(firstboss.getHealth());
		levelView.firstBossUpdateHealthDisplayPosition(firstboss);

		if (firstboss.isShielded()) levelView.firstBossShowShield();
		else levelView.firstBossHideShield();

		if (firstboss.isDestroyed()) levelView.firstBossHideBossHealth();

		levelView.secondBossUpdateShieldPosition(secondBoss);
		levelView.secondBossUpdateBossHealth(secondBoss.getHealth());
		levelView.secondBossUpdateHealthDisplayPosition(secondBoss);

		if (secondBoss.isShielded()) levelView.secondBossShowShield();
		else levelView.secondBossHideShield();

		if(secondBoss.isDestroyed()) levelView.secondBossHideBossHealth();
	}

	private void spawnSecondBoss() {
		addEnemyUnit(secondBoss);
		levelView.secondBossDisplayShield();
		levelView.SecondBossShowBossHealth();
	}
}
