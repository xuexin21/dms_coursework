package com.example.demo.level.levelview;

import com.example.demo.controller.Main;
import com.example.demo.view.GameOverImage;
import com.example.demo.view.HeartDisplay;
import com.example.demo.view.WinImage;
import javafx.scene.Group;

/**
 * The LevelView class is responsible for managing the visual elements related to the level.
 * It handles the display of heart icons, win screen, and game over screen.
 */
public class LevelView {

	/**
	 * The X position of the heart display relative to the screen.
	 */
	private static final double HEART_DISPLAY_X_POSITION = (double) Main.SCREEN_WIDTH / 2 - 645;

	/**
	 * The Y position of the heart display relative to the screen.
	 */
	private static final double HEART_DISPLAY_Y_POSITION = (double) Main.SCREEN_HEIGHT / 2 - 350;

	/**
	 * The X position of the win image relative to the screen.
	 */
	private static final double WIN_IMAGE_X_POSITION = (double) Main.SCREEN_WIDTH / 2 - 225;

	/**
	 * The Y position of the win image relative to the screen.
	 */
	private static final double WIN_IMAGE_Y_POSITION = (double) Main.SCREEN_HEIGHT / 2 - 200;

	/**
	 * The X position of the game over screen relative to the screen.
	 */
	private static final double LOSS_SCREEN_X_POSITION = (double) Main.SCREEN_WIDTH / 2 - 330;

	/**
	 * The Y position of the game over screen relative to the screen.
	 */
	private static final double LOSS_SCREEN_Y_POSITION = (double) Main.SCREEN_HEIGHT / 2 - 285;

	/**
	 * The root group that holds all the visual elements of the level.
	 */
	private final Group root;

	/**
	 * The WinImage object for displaying the win screen.
	 */
	private final WinImage winImage;

	/**
	 * The GameOverImage object for displaying the game over screen.
	 */
	private final GameOverImage gameOverImage;

	/**
	 * The HeartDisplay object for managing the display of hearts.
	 */
	private final HeartDisplay heartDisplay;

	/**
	 * Constructs a LevelView object that manages the display of hearts, win image, and game over image.
	 *
	 * @param root the root group that holds the visual elements of the level
	 * @param heartsToDisplay the initial number of hearts to display
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
	}

	/**
	 * Displays the heart display on the screen.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Hides the heart display from the screen.
	 */
	public void hideHeartDisplay() {
		root.getChildren().remove(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game over image on the screen.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
		gameOverImage.showGameOverImage();
	}

	/**
	 * Removes hearts from the heart display to match the specified number of remaining hearts.
	 *
	 * @param heartsRemaining the number of hearts to display after removal
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Adds hearts to the heart display to match the specified number of remaining hearts.
	 *
	 * @param heartsRemaining the number of hearts to display after addition
	 */
	public void addHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < heartsRemaining - currentNumberOfHearts; i++) {
			heartDisplay.addHeart();
		}
	}
}
