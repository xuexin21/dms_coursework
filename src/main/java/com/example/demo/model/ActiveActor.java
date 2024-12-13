package com.example.demo.model;

import javafx.scene.image.*;

/**
 * Represents an active actor in the game, such as a character or object that can move and be displayed on the screen.
 * Extends {@link ImageView} to allow for graphical representation and positioning of the actor in the game scene.
 */
public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs a new ActiveActor object with the specified image, height, and initial position.
	 * The image is loaded from the resources directory.
	 *
	 * @param imageName   The name of the image file to be used for the actor.
	 * @param imageHeight The height of the actor's image.
	 * @param initialXPos The initial X position of the actor.
	 * @param initialYPos The initial Y position of the actor.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor. This method is abstract and must be implemented by subclasses
	 * to define how the actor's position is updated (e.g., movement, animation).
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by the specified amount.
	 * This method alters the actor's current X position by the given horizontal move.
	 *
	 * @param horizontalMove The amount to move the actor along the X axis.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by the specified amount.
	 * This method alters the actor's current Y position by the given vertical move.
	 *
	 * @param verticalMove The amount to move the actor along the Y axis.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
