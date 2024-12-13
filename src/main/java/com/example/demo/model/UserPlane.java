package com.example.demo.model;

import com.example.demo.controller.Main;
import com.example.demo.projectile.UserProjectile;

/**
 * The UserPlane class represents the player's plane in the game. It is an extension of the {@link FighterPlane} class.
 * The user plane can move vertically and horizontally, fire projectiles, and track the player's progress, such as the number of kills.
 * It also has methods to adjust the speed, give extra lives, and reset its state.
 */
public class UserPlane extends FighterPlane {

	/**
	 * The image file name used to represent the user's plane in the game.
	 */
	private static final String IMAGE_NAME = "userplane.png";

	/**
	 * The upper bound for the Y position (top of the screen).
	 */
	private static final double Y_UPPER_BOUND = 5;

	/**
	 * The lower bound for the Y position (bottom of the screen).
	 */
	private static final double Y_LOWER_BOUND = (double) Main.SCREEN_HEIGHT - 90;

	/**
	 * The upper bound for the X position (left edge of the screen).
	 */
	private static final double X_UPPER_BOUND = 0;

	/**
	 * The lower bound for the X position (right edge of the screen).
	 */
	private static final double X_LOWER_BOUND = (double) Main.SCREEN_WIDTH - 700;

	/**
	 * The initial X position of the user's plane.
	 */
	private static final double INITIAL_X_POSITION = 5.0;

	/**
	 * The initial Y position of the user's plane.
	 */
	private static final double INITIAL_Y_POSITION = 300.0;

	/**
	 * The height of the user's plane image.
	 */
	private static final int IMAGE_HEIGHT = 40;

	/**
	 * The vertical velocity of the user's plane, determining how much the plane moves vertically per cycle.
	 */
	private int VERTICAL_VELOCITY = 10;

	/**
	 * The horizontal velocity of the user's plane, determining how much the plane moves horizontally per cycle.
	 */
	private int HORIZONTAL_VELOCITY = 10;

	/**
	 * The horizontal position offset for the user's projectile.
	 */
	private static final int PROJECTILE_X_POSITION = 140;

	/**
	 * The vertical offset for the user's projectile.
	 */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 21;

	/**
	 * A multiplier for the vertical velocity, used to control the movement direction of the plane.
	 */
	private int verticalVelocityMultiplier;

	/**
	 * A multiplier for the horizontal velocity, used to control the movement direction of the plane.
	 */
	private int horizontalVelocityMultiplier;

	/**
	 * The number of kills the user has made.
	 */
	private int numberOfKills;

	/**
	 * Constructs a new UserPlane with the specified initial health.
	 *
	 * @param initialHealth The initial health value of the user's plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user's plane. The plane can move vertically and horizontally based on the user's input.
	 * The movement is constrained within the defined screen bounds.
	 */
	@Override
	public void updatePosition() {
		if (isMovingVertically()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
		if (isMovingHorizontally()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newPosition = getLayoutX() + getTranslateX();
			if (newPosition < X_UPPER_BOUND || newPosition > X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Updates the state of the user's plane by calling the {@link #updatePosition} method.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user's plane. The projectile is fired from a position offset relative to the plane.
	 *
	 * @return A new {@link UserProjectile} representing the projectile fired by the user's plane.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION);
		double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
		return new UserProjectile(projectileXPosition, projectileYPosition);
	}

	/**
	 * Checks if the plane is moving vertically.
	 *
	 * @return True if the plane is moving vertically, otherwise false.
	 */
	private boolean isMovingVertically() {
		return verticalVelocityMultiplier != 0;
	}

	/**
	 * Checks if the plane is moving horizontally.
	 *
	 * @return True if the plane is moving horizontally, otherwise false.
	 */
	private boolean isMovingHorizontally() {
		return horizontalVelocityMultiplier != 0;
	}

	/**
	 * Moves the user's plane upwards by setting the vertical velocity multiplier to -1.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user's plane downwards by setting the vertical velocity multiplier to 1.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the user's plane to the left by setting the horizontal velocity multiplier to -1.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user's plane to the right by setting the horizontal velocity multiplier to 1.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stops the vertical movement of the user's plane by setting the vertical velocity multiplier to 0.
	 */
	public void stopVertically() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops the horizontal movement of the user's plane by setting the horizontal velocity multiplier to 0.
	 */
	public void stopHorizontally() {
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Gets the current number of kills made by the user's plane.
	 *
	 * @return The number of kills made by the user.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the number of kills made by the user's plane.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

	/**
	 * Gives the user an extra life if the health is less than the maximum allowed.
	 */
	public void giveExtraLife() {
		if (this.health < 5) {
			health++;
		}
	}

	/**
	 * Increases the speed of the user's plane by increasing both the vertical and horizontal velocities.
	 * The speed will not exceed a certain limit.
	 */
	public void increaseSpeed() {
		if (this.VERTICAL_VELOCITY <= 20 && this.HORIZONTAL_VELOCITY <= 20) {
			this.VERTICAL_VELOCITY += 2;
			this.HORIZONTAL_VELOCITY += 2;
		}
	}

	/**
	 * Increases the speed of the user's projectiles.
	 */
	public void increaseProjectileSpeed() {
		UserProjectile.increaseProjectileVelocity();
	}

	/**
	 * Resets the plane's speed and the speed of its projectiles to the default values.
	 */
	public void reset() {
		HORIZONTAL_VELOCITY = 10;
		VERTICAL_VELOCITY = 10;
		UserProjectile.resetProjectileVelocity();
	}

	/**
	 * Gets the current vertical velocity of the user's plane.
	 *
	 * @return The vertical velocity of the user's plane.
	 */
	public int getVerticalVelocity() {
		return VERTICAL_VELOCITY;
	}

	/**
	 * Gets the current horizontal velocity of the user's plane.
	 *
	 * @return The horizontal velocity of the user's plane.
	 */
	public int getHorizontalVelocity() {
		return HORIZONTAL_VELOCITY;
	}
}
