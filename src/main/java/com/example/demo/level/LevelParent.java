package com.example.demo.level;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.audio.*;
import com.example.demo.model.UserPlane;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.FighterPlane;
import com.example.demo.controller.Main;
import com.example.demo.controller.inGame.LevelMenu;
import com.example.demo.controller.inGame.LoseMenu;
import com.example.demo.controller.inGame.PauseButton;
import com.example.demo.controller.inGame.PauseMenu;
import com.example.demo.controller.inGame.WinMenu;
import com.example.demo.level.levelview.LevelView;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * Abstract base class for defining the level logic in the game.
 * It manages the game loop, entities (players, enemies, projectiles), and game state.
 */
public abstract class LevelParent extends Observable {

	/**
	* Constant for adjusting the screen height for various entities
	*/
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/**
	 * Constant for the delay between each frame in the game loop (in milliseconds)
	 */
	private static final int MILLISECOND_DELAY = 50;

	/**
	 * The height of the game screen
	 */
	private final double screenHeight;

	/**
	 * The width of the game screen
	 */
	private final double screenWidth;

	/**
	 * The maximum Y position the enemy units can move to on the screen
	 */
	private final double enemyMaximumYPosition;

	/**
	 * The maximum Y position the butterfly units can move to on the screen
	 */
	private final double butterflyMaximumYPosition;

	/**
	 * The maximum Y position the obstacle units can move to on the screen
	 */
	private final double obstacleMaximumYPosition;

	/**
	 * The root group containing all game entities
	 */
	private final Group root;

	/**
	 * The timeline controlling the game loop
	 */
	private final Timeline timeline;

	/**
	 * The scene representing the game view
	 */
	private final Scene scene;

	/**
	 * The background image for the level
	 */
	private final ImageView background;

	/**
	 * The user-controlled plane object
	 */
	public static UserPlane user;

	/**
	 * List of all friendly (player) units in the game
	 */
	private final List<ActiveActorDestructible> friendlyUnits;

	/**
	 * List of all enemy units in the game
	 */
	private final List<ActiveActorDestructible> enemyUnits;

	/**
	 * List of all projectiles fired by the player
	 */
	private final List<ActiveActorDestructible> userProjectiles;

	/**
	 * List of all projectiles fired by enemies
	 */
	private final List<ActiveActorDestructible> enemyProjectiles;

	/**
	 * List of all butterfly units in the game
	 */
	private final List<ActiveActorDestructible> butterflyUnits;

	/**
	 * List of all obstacle units in the game
	 */
	private final List<ActiveActorDestructible> obstacleUnits;

	/**
	 * The current level's name
	 */
	public static String currentLevelName = "com.example.demo.level.LevelOne";

	/**
	 * The next level's name
	 */
	public static String nextLevelName = "com.example.demo.level.LevelTwo";

	/**
	 * The music manager responsible for background music
	 */
	private final Music music;

	/**
	 * The sound manager responsible for sound effects
	 */
	private final Sound sound;

	/**
	 * Menu that shows when the player loses the game
	 */
	private LoseMenu loseMenu;

	/**
	 * Menu that shows when the player wins the game
	 */
	private WinMenu winMenu;

	/**
	 * Menu that shows level options
	 */
	private LevelMenu levelMenu;

	/**
	 * The button used to pause the game
	 */
	private PauseButton pauseButton;

	/**
	 * Menu that appears when the game is paused
	 */
	private PauseMenu pauseMenu;

	/**
	 * Main class responsible for controlling the game flow
	 */
	private final Main main = new Main();

	/**
	 * Flag indicating whether the game is paused
	 */
	private boolean isPaused = false;

	/**
	 * Flag indicating whether the game is currently active
	 */
	private boolean gameActive = true;

	/**
	 * The number of enemies currently in the game
	 */
	private int currentNumberOfEnemies;

	/**
	 * The view of the current level
	 */
	private LevelView levelView;

	/**
	 * Constructs a new LevelParent object to manage the level.
	 *
	 * @param backgroundImageName The name of the background image for the level.
	 * @param screenHeight The height of the screen.
	 * @param screenWidth The width of the screen.
	 * @param playerInitialHealth The initial health of the player.
	 * @param music The background music object for the level.
	 * @param sound The sound manager for sound effects.
	 */
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
		loseMenu = new LoseMenu(this, this::exitGame, this::returnToMenu, music, sound);
		winMenu = new WinMenu(this::exitGame, this::returnToMenu, music, sound);
		levelMenu = new LevelMenu(this, this::returnToMenu, music, sound);
		this.pauseButton = new PauseButton(this::pauseGame, sound);
		pauseMenu = new PauseMenu(this::resumeGame, this::returnToMenu, music,sound);
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Abstract method to initialize friendly units (e.g., player and allies).
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Abstract method to check if the game is over based on certain conditions.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method to spawn enemy units in the level.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Spawn butterfly units.
	 */
	protected void spawnButterflyUnits() {};

	/**
	 * Spawn obstacle units.
	 */
	protected void spawnObstacle() {};

	/**
	 * Abstract method to instantiate the level view for the current level.
	 *
	 * @return The LevelView object representing the current level's view.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the game scene with background, friendly units, and other elements.
	 *
	 * @return The initialized scene object.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		root.getChildren().addAll(pauseButton, pauseMenu, loseMenu, winMenu, levelMenu);
		return scene;
	}

	/**
	 * Starts the game by playing background music and starting the game loop.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
		music.playBackgroundMusic();
	}

	/**
	 * Moves to the next level and stops the current game timeline.
	 *
	 * @param levelName The name of the next level to transition to.
	 */
	public void goToNextLevel(String levelName) {
		timeline.stop();
		setChanged();
		notifyObservers(levelName);
	}

	/**
	 * Checks if the game should progress to the next level.
	 *
	 * @param levelName The name of the next level to transition to.
	 */
	public void checkToNextLevel(String levelName) {
		timeline.stop();
		clearAllActors();
		nextLevelName = levelName;
		pauseButton.hidePauseButton();
		levelView.hideHeartDisplay();
		levelMenu.showLevelMenu();
		levelMenu.setVisible(true);
		gameActive = false; // Disable gameplay
	}

	/**
	 * Replays the current level and shows the game over screen.
	 *
	 * @param levelName The name of the level to replay.
	 */
	public void replayThisLevel(String levelName) {
		timeline.stop();
		clearAllActors();
		currentLevelName = levelName;
		music.stopBackgroundMusic();
		levelView.showGameOverImage();
		sound.playGameOverSound();
		pauseButton.hidePauseButton();
		levelView.hideHeartDisplay();
		loseMenu.showLoseMenu();
		loseMenu.setVisible(true);
		gameActive = false;
	}

	/**
	 * Updates the game state each frame (spawns enemies, updates entities, etc.).
	 */
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

	/**
	 * Initializes the game loop that runs indefinitely.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background image for the level and sets up key/mouse events.
	 */
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

	/**
	 * Fires a projectile from the player character.
	 */
	private void fireProjectile() {
		if (!gameActive) {
			return;
		}
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
		sound.playUserProjectileSound();
	}

	/**
	 * Generates enemy projectiles based on enemy actions.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns an enemy projectile and adds it to the game.
	 *
	 * @param projectile The projectile to be added.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
			sound.playEnemyProjectileSound();
		}
	}

	/**
	 * Updates all game actors (units, projectiles, etc.) each frame.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
		butterflyUnits.forEach(butterfly -> butterfly.updateActor());
		obstacleUnits.forEach(obstacle -> obstacle.updateActor());
	}

	/**
	 * Removes all destroyed actors from the game.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
		removeDestroyedActors(butterflyUnits);
	}

	/**
	 * Removes destroyed actors from a list and the root group.
	 *
	 * @param actors The list of actors to be checked for destruction.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Clears all actors (friendly, enemy, projectiles, etc.) from the game.
	 */
	private void clearAllActors() {
		root.getChildren().removeAll(friendlyUnits);
		root.getChildren().removeAll(enemyUnits);
		root.getChildren().removeAll(userProjectiles);
		root.getChildren().removeAll(enemyProjectiles);
		root.getChildren().removeAll(butterflyUnits);
		root.getChildren().removeAll(obstacleUnits);

		friendlyUnits.clear();
		enemyUnits.clear();
		userProjectiles.clear();
		enemyProjectiles.clear();
		butterflyUnits.clear();
		obstacleUnits.clear();
	}

	/**
	 * Handles collisions between friendly units and enemy units.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handles collisions between fire projectiles (friendly and enemy) and each other.
	 */
	private void handleFireProjectileCollisions() {
		handleCollisions(enemyProjectiles, userProjectiles);
	}

	/**
	 * Handles collisions between butterflies and user projectiles.
	 */
	private void handleButterfliesCollisions() {
		handleCollisions(userProjectiles, butterflyUnits);
	}

	/**
	 * Handles sound effects triggered by collisions.
	 */
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

	/**
	 * Handles collisions between two lists of actors, applying damage to both.
	 *
	 * @param actors1 The first list of actors.
	 * @param actors2 The second list of actors.
	 */
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

	/**
	 * Handles enemy penetration and applies damage to the player if an enemy penetrates the defenses.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the level view to reflect the player's current health.
	 */
	protected void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		levelView.addHearts(user.getHealth());
	}

	/**
	 * Updates the kill count based on the number of enemies destroyed.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if an enemy has penetrated the player's defenses.
	 *
	 * @param enemy The enemy to check.
	 * @return True if the enemy has penetrated, false otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Handles the game's win condition and stops the game.
	 */
	protected void winGame() {
		timeline.stop();
		clearAllActors();
		levelView.hideHeartDisplay();
		music.stopBackgroundMusic();
		sound.playWinSound();
		levelView.showWinImage();
		winMenu.showWinMenu();
		winMenu.setVisible(true);
		pauseButton.hidePauseButton();
		gameActive = false;
	}

	/**
	 * Retrieves the current user plane.
	 *
	 * @return The user plane.
	 */
	public static UserPlane getUser() {
		return user;
	}

	/**
	 * Retrieves the root group of the game.
	 *
	 * @return The root group.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Retrieves the number of enemies currently in the game.
	 *
	 * @return The current number of enemies.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the game.
	 *
	 * @param enemy The enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Adds a butterfly unit to the game.
	 *
	 * @param butterfly The butterfly unit to add.
	 */
	protected void addButterflyUnit(ActiveActorDestructible butterfly)	{
		butterflyUnits.add(butterfly);
		root.getChildren().add(butterfly);
	}

	/**
	 * Adds an obstacle unit to the game.
	 *
	 * @param obstacle The obstacle unit to add.
	 */
	protected void addObstacleUnit(ActiveActorDestructible obstacle)	{
		obstacleUnits.add(obstacle);
		root.getChildren().add(obstacle);
	}

	/**
	 * Retrieves the maximum Y position for enemy units.
	 *
	 * @return The maximum Y position.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Retrieves the maximum Y position for butterfly units.
	 *
	 * @return The maximum Y position.
	 */
	protected double getButterflyMaximumYPosition() {
		return butterflyMaximumYPosition;
	}

	/**
	 * Retrieves the maximum Y position for obstacle units.
	 *
	 * @return The maximum Y position.
	 */
	protected double getObstacleMaximumYPosition () {
		return obstacleMaximumYPosition;
	}

	/**
	 * Retrieves the width of the screen.
	 *
	 * @return The screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user plane is destroyed.
	 *
	 * @return True if the user plane is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the current number of enemies in the game.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	/**
	 * Pauses the game and shows the pause menu.
	 */
	private void pauseGame() {
		timeline.pause();
		music.pauseBackgroundMusic();
		pauseMenu.showPauseMenu();
		pauseMenu.toFront();
		pauseMenu.setVisible(true);
		isPaused = true;
	}

	/**
	 * Exits the game by closing the current window.
	 */
	private void exitGame() {
		timeline.stop();
		music.stopBackgroundMusic();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

	/**
	 * Resumes the game after it has been paused.
	 */
	private void resumeGame() {
		timeline.play();
		music.playBackgroundMusic();
		isPaused = false;
	}

	/**
	 * Returns to the main menu.
	 */
	private void returnToMenu() {
		try {
			sound.stopGameOverSound();
			sound.stopWinSound();
			winMenu.setVisible(false);
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
