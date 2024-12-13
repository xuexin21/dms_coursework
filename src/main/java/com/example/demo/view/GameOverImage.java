package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The GameOverImage class represents the game over image that is displayed when the player loses the game.
 * It is an image view component that shows the "Game Over" image at a specific location on the screen.
 */
public class GameOverImage extends ImageView {

	/**
	 * The path to the "Game Over" image file.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * The width of the "Game Over" image.
	 */
	private static final double WIDTH = Main.SCREEN_WIDTH / 2 - 50;

	/**
	 * The height of the "Game Over" image.
	 */
	private static final double HEIGHT = Main.SCREEN_HEIGHT / 2 + 25;

	/**
	 * Constructs a new GameOverImage and initializes it with the specified position on the screen.
	 * The image is loaded from the file path and resized to fit the specified width and height.
	 *
	 * @param xPosition The X position of the "Game Over" image on the screen.
	 * @param yPosition The Y position of the "Game Over" image on the screen.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.toFront();
	}

	/**
	 * Makes the "Game Over" image visible on the screen.
	 * This method is called to show the image when the game is over.
	 */
	public void showGameOverImage() {
		this.setVisible(true);
	}
}
