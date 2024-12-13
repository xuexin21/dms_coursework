package com.example.demo.model;

import com.example.demo.projectile.EnemyProjectile;

/**
 * The EnemyPlane class represents an enemy fighter plane in the game.
 * It is a type of FighterPlane that moves horizontally across the screen
 * and can fire projectiles at the player.
 */
public class EnemyPlane extends FighterPlane {

	/**
	 * The image file name for the enemy plane.
	 */
	private static final String IMAGE_NAME = "enemyplane.png";

	/**
	 * The height of the enemy plane image.
	 */
	private static final int IMAGE_HEIGHT = 55;

	/**
	 * The horizontal velocity (movement speed) of the enemy plane.
	 * A negative value indicates movement towards the left.
	 */
	private static final int HORIZONTAL_VELOCITY = -6;

	/**
	 * The X-axis offset for the projectile's initial position relative to the enemy plane.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -50.0;

	/**
	 * The Y-axis offset for the projectile's initial position relative to the enemy plane.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 22.0;

	/**
	 * The initial health of the enemy plane.
	 */
	private static final int INITIAL_HEALTH = 1;

	/**
	 * The fire rate of the enemy plane, indicating the probability of firing a projectile per frame.
	 * A value of 0.02 means there is a 2% chance of firing a projectile each frame.
	 */
	private static final double FIRE_RATE = .02;


	/**
	 * Constructs an EnemyPlane at the given initial position.
	 *
	 * @param initialXPos the initial X position of the enemy plane
	 * @param initialYPos the initial Y position of the enemy plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane. The plane moves horizontally to the left.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane. The projectile is created if the
	 * random condition for fire rate is met.
	 *
	 * @return a new EnemyProjectile if the fire rate condition is met, otherwise null
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the behavior of the enemy plane. This method calls the updatePosition
	 * method to move the plane across the screen.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
