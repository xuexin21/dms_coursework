package com.example.demo.projectile;

/**
 * The EnemyProjectile class represents a projectile fired by an enemy in the game. It extends the {@link Projectile} class.
 * This projectile moves horizontally at a constant speed and is visually represented by an enemy fire image.
 */
public class EnemyProjectile extends Projectile {

	/**
	 * The image file name used to represent the enemy's projectile in the game.
	 */
	private static final String IMAGE_NAME = "enemyFire.png";

	/**
	 * The height of the enemy's projectile image.
	 */
	private static final int IMAGE_HEIGHT = 18;

	/**
	 * The horizontal velocity of the enemy's projectile, determining how fast it moves horizontally across the screen.
	 */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs a new EnemyProjectile with the specified initial X and Y positions.
	 *
	 * @param initialXPos The initial X position of the enemy's projectile on the screen.
	 * @param initialYPos The initial Y position of the enemy's projectile on the screen.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy's projectile by moving it horizontally at the defined speed.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the enemy's projectile by calling the {@link #updatePosition} method.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
