package com.example.demo.projectile;

/**
 * The UserProjectile class represents a projectile fired by the user's plane. It extends {@link Projectile}
 * and defines the specific behavior of the projectile fired by the player in the game.
 * This class includes functionality for movement, speed adjustments, and velocity control.
 */
public class UserProjectile extends Projectile {

	/**
	 * The image name representing the user projectile.
	 */
	private static final String IMAGE_NAME = "userfire.png";

	/**
	 * The height of the user projectile image.
	 */
	private static final int IMAGE_HEIGHT = 12;

	/**
	 * The horizontal velocity of the user projectile.
	 * The velocity determines how fast the projectile moves across the screen.
	 */
	public static int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a new UserProjectile with the specified initial X and Y positions.
	 * The projectile's image name, height, and initial position are passed to the superclass constructor.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the user projectile by moving it horizontally
	 * with the current horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the actor, which in this case involves updating the projectile's position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Increases the horizontal velocity of the user projectile, making it move faster.
	 * The velocity is incremented by 3, but it will not exceed a maximum value of 30.
	 */
	public static void increaseProjectileVelocity() {
		if (HORIZONTAL_VELOCITY < 30) {
			HORIZONTAL_VELOCITY += 3;
		}
	}

	/**
	 * Resets the horizontal velocity of the user projectile back to its default value of 15.
	 */
	public static void resetProjectileVelocity() {
		HORIZONTAL_VELOCITY = 15;
	}

	/**
	 * Gets the current horizontal velocity of the user projectile.
	 *
	 * @return The current horizontal velocity of the user projectile.
	 */
	public static int getProjectileSpeed() {
		return HORIZONTAL_VELOCITY;
	}
}