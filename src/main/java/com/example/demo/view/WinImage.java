package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The WinImage class is responsible for displaying the "You Win" image when the player wins the game.
 * It provides a method to show the "You Win" image and control its visibility.
 */
public class WinImage extends ImageView {

	/**
	 * The path to the "You Win" image resource.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";

	/**
	 * The height of the "You Win" image.
	 */
	private static final int HEIGHT = 300;

	/**
	 * The width of the "You Win" image.
	 */
	private static final int WIDTH = 450;

	/**
	 * Constructs a new WinImage object, initializes the "You Win" image, and sets its visibility to false.
	 * The image is resized to the specified WIDTH and HEIGHT and positioned at the specified coordinates.
	 *
	 * @param xPosition The x-coordinate where the "You Win" image will be positioned.
	 * @param yPosition The y-coordinate where the "You Win" image will be positioned.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // Initially set the image to be invisible
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.toFront();
	}

	/**
	 * Makes the "You Win" image visible on the screen.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}
