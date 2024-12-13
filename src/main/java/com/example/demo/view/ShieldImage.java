package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ShieldImage class is responsible for displaying and controlling the shield image in the game.
 * It provides methods to show, hide, and position the shield image on the screen.
 */
public class ShieldImage extends ImageView {

	/**
	 * The path to the shield image resource.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";

	/**
	 * The size of the shield image (width and height).
	 */
	public static final int SHIELD_SIZE = 60;

	/**
	 * Constructs a new ShieldImage object, initializes the shield image, and sets its visibility to false.
	 * The image is resized to the specified SHIELD_SIZE.
	 */
	public ShieldImage() {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // Initially set the shield to be invisible
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Makes the shield image visible on the screen.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield image from the screen.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

	/**
	 * Sets the position of the shield image on the screen.
	 *
	 * @param x The x-coordinate of the shield's position.
	 * @param y The y-coordinate of the shield's position.
	 */
	public void setLayout(double x, double y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
}
