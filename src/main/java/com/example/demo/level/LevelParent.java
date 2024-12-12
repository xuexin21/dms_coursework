package com.example.demo.level;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.level.levelview.LevelView;
import com.example.demo.audio.*;
import com.example.demo.model.UserPlane;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.FighterPlane;
import com.example.demo.controller.Main;
import com.example.demo.ui.ContinueButton;
import com.example.demo.ui.ContinueMenu;
import com.example.demo.ui.PauseButton;
import com.example.demo.ui.PauseMenu;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;
	private final double butterflyMaximumYPosition;
	private final double obstacleMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	public static UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;
	private final List<ActiveActorDestructible> butterflyUnits;
	private final List<ActiveActorDestructible> obstacleUnits;

	private final Music music;
	private final Sound sound;
	private ContinueButton continueButton;
	private ContinueMenu continueMenu;
	private PauseButton pauseButton;
	private PauseMenu pauseMenu;
	private final Main main = new Main();
	private boolean isPaused = false;
	private boolean gameActive = true;
	
	private int currentNumberOfEnemies;
	private LevelView levelView;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Music music, Sound sound) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.butterflyUnits = new ArrayList<>();
		this.obstacleUnits = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.butterflyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.obstacleMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		this.music = music;
		this.sound = sound;
		this.continueButton = new ContinueButton(this::continueGame, sound);
		continueMenu = new ContinueMenu(this::exitGame, this::returnToMenu, music, sound);
		this.pauseButton = new PauseButton(this::pauseGame, sound);
		pauseMenu = new PauseMenu(this::resumeGame, this::returnToMenu, music,sound);
		initializeTimeline();
		friendlyUnits.add(user);
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected void spawnButterflyUnits() {};

	protected void spawnObstacle() {};

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		root.getChildren().addAll(pauseButton, pauseMenu, continueButton, continueMenu);
		return scene;
	}

	public void startGame() {
		background.requestFocus();
		timeline.play();
		music.playBackgroundMusic();
	}

	public void goToNextLevel(String levelName) {
		timeline.stop();
		sound.playNextLevelSound();
		setChanged();
		notifyObservers(levelName);
	}

	private void updateScene() {
		spawnEnemyUnits();
		spawnButterflyUnits();
		spawnObstacle();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handleFireProjectileCollisions();
		handlePlaneCollisions();
		handleSoundCollisions();
		handleButterfliesCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if (!gameActive) return;
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.W) user.moveUp();
				if (kc == KeyCode.DOWN || kc == KeyCode.S) user.moveDown();
				if (kc == KeyCode.LEFT || kc == KeyCode.A) user.moveLeft();
				if (kc == KeyCode.RIGHT || kc == KeyCode.D) user.moveRight();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if (!gameActive) return;
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN || kc == KeyCode.W || kc == KeyCode.S) user.stopVertically();
				if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT || kc == KeyCode.A || kc == KeyCode.D) user.stopHorizontally();
			}
		});
		background.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (!gameActive) return;
				if (e.getButton() == MouseButton.PRIMARY) {
					fireProjectile();
				}
			}
		});
		root.getChildren().add(background);
	}

	private void fireProjectile() {
		if (!gameActive) {
			return; // Do nothing if the game is not active
		}
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
		sound.playUserProjectileSound();
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
			sound.playEnemyProjectileSound();
		}
	}

	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
		butterflyUnits.forEach(butterfly -> butterfly.updateActor());
		obstacleUnits.forEach(obstacle -> obstacle.updateActor());
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
		removeDestroyedActors(butterflyUnits);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void clearAllActors() {
		// Remove all actors from their respective lists and from the root group
		root.getChildren().removeAll(friendlyUnits);
		root.getChildren().removeAll(enemyUnits);
		root.getChildren().removeAll(userProjectiles);
		root.getChildren().removeAll(enemyProjectiles);
		root.getChildren().removeAll(butterflyUnits);
		root.getChildren().removeAll(obstacleUnits);

		// Clear the lists
		friendlyUnits.clear();
		enemyUnits.clear();
		userProjectiles.clear();
		enemyProjectiles.clear();
		butterflyUnits.clear();
		obstacleUnits.clear();
	}

	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleFireProjectileCollisions() {
		handleCollisions(enemyProjectiles, userProjectiles);
	}

	private void handleButterfliesCollisions() {
		handleCollisions(userProjectiles, butterflyUnits);
	}

	private void handleSoundCollisions() {
		for (ActiveActorDestructible actor : enemyUnits) {
			if (actor.isDestroyed()) sound.playExplosionSound();
		}

		for (ActiveActorDestructible actor : friendlyUnits) {
			if (actor.isDestroyed()) sound.playExplosionSound();
		}

		for (ActiveActorDestructible actor : butterflyUnits) {
			for (ActiveActorDestructible otherActor : userProjectiles) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					sound.playPowerUpSound();
				}
			}
		}
	}

	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	protected void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		levelView.addHearts(user.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		timeline.stop();
		clearAllActors(); // Clear all entities
		music.stopBackgroundMusic();
		levelView.showWinImage();
		pauseButton.hidePauseButton();
		continueButton.showContinueButton();
		gameActive = false; // Disable gameplay
	}

	protected void loseGame() {
		timeline.stop();
		clearAllActors(); // Clear all entities
		music.stopBackgroundMusic();
		levelView.showGameOverImage();
		sound.playGameOverSound();
		pauseButton.hidePauseButton();
		continueButton.showContinueButton();
		gameActive = false; // Disable gameplay
	}

	public static UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected void addButterflyUnit(ActiveActorDestructible butterfly)	{
		butterflyUnits.add(butterfly);
		root.getChildren().add(butterfly);
	}

	protected void addObstacleUnit(ActiveActorDestructible obstacle)	{
		obstacleUnits.add(obstacle);
		root.getChildren().add(obstacle);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getButterflyMaximumYPosition() {
		return butterflyMaximumYPosition;
	}

	protected double getObstacleMaximumYPosition () {
		return obstacleMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	private void pauseGame() {
		timeline.pause();
		music.pauseBackgroundMusic();
		pauseMenu.showPauseMenu();
		pauseMenu.toFront();
		pauseMenu.setVisible(true);
		isPaused = true;
	}

	private void continueGame() {
		timeline.pause();
		music.pauseBackgroundMusic();
		continueMenu.showContinueMenu();
		continueMenu.toFront();
		continueMenu.setVisible(true);
		isPaused = true;
	}

	private void exitGame() {
		timeline.stop();
		music.stopBackgroundMusic();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	private void resumeGame() {
		timeline.play();
		music.playBackgroundMusic();
		isPaused = false;
	}

	private void returnToMenu() {
		try {
			continueMenu.setVisible(false);
			timeline.stop();
			Stage stage = (Stage) root.getScene().getWindow();
			stage.sizeToScene();
			main.start(stage);
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
				 | IllegalAccessException | InvocationTargetException e) {
			System.err.println("Error returning to the main menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
