package com.example.demo.model;

/**
 * The Obstacle class represents an indestructible obstacle in the game, such as clouds.
 * The obstacle moves horizontally across the screen at a slower speed than other enemies.
 * It is a subclass of {@link ActiveActorDestructible}, but it does not take damage.
 */
public class Obstacle extends ActiveActorDestructible {

	/**
	 * The image file name representing the obstacle (cloud) in the game.
	 */
	private static final String IMAGE_NAME = "cloud.png";

	/**
	 * The height of the obstacle (cloud) image.
	 */
	private static final int IMAGE_HEIGHT = 450;

	/**
	 * The horizontal velocity of the obstacle, defining the speed at which it moves.
	 * Clouds move slower than enemies.
	 */
	private static final int HORIZONTAL_VELOCITY = -3;

	/**
	 * Constructs a new obstacle with the specified initial position.
	 * The obstacle is indestructible and is displayed behind other elements.
	 *
	 * @param initialXPos the initial X position of the obstacle on the screen.
	 * @param initialYPos the initial Y position of the obstacle on the screen.
	 */
	public Obstacle(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		setViewOrder(-1);
	}

	/**
	 * Updates the position of the obstacle by moving it horizontally at a constant velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the obstacle in each game frame.
	 * Currently, this only updates the position of the obstacle.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * The obstacle does not take damage, so this method does nothing.
	 */
	@Override
	public void takeDamage() {
		// Clouds don't take damage; override to do nothing
	}
}

