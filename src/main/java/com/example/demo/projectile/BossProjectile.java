package com.example.demo.projectile;

/**
 * The BossProjectile class represents a projectile fired by the boss in the game. It extends the {@link Projectile} class.
 * This projectile moves horizontally at a constant speed and is visually represented by a fireball image.
 */
public class BossProjectile extends Projectile {

	/**
	 * The image file name used to represent the boss's projectile in the game.
	 */
	private static final String IMAGE_NAME = "fireball.png";

	/**
	 * The height of the boss's projectile image.
	 */
	private static final int IMAGE_HEIGHT = 50;

	/**
	 * The horizontal velocity of the boss's projectile, determining how fast it moves horizontally across the screen.
	 */
	private static final int HORIZONTAL_VELOCITY = -15;

	/**
	 * The initial X position of the boss's projectile on the screen.
	 */
	private static final int INITIAL_X_POSITION = 800;

	/**
	 * Constructs a new BossProjectile with the specified initial Y position.
	 *
	 * @param initialYPos The initial Y position of the boss's projectile on the screen.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the boss's projectile by moving it horizontally at the defined speed.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the boss's projectile by calling the {@link #updatePosition} method.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
