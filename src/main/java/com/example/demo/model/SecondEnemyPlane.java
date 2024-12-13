package com.example.demo.model;

import com.example.demo.projectile.EnemyProjectile;

/**
 * The SecondEnemyPlane class represents a specific type of enemy plane in the game.
 * It is an extension of {@link FighterPlane} and has properties like its own image,
 * movement pattern, projectile firing behavior, and health.
 */
public class SecondEnemyPlane extends FighterPlane {

	/**
	 * The image file name used to represent the second enemy plane in the game.
	 */
	private static final String IMAGE_NAME = "enemyplane2.png";

	/**
	 * The height of the second enemy plane image.
	 */
	private static final int IMAGE_HEIGHT = 50;

	/**
	 * The horizontal velocity (speed) of the second enemy plane, determining how fast it moves left.
	 */
	private static final int HORIZONTAL_VELOCITY = -6;

	/**
	 * The horizontal offset for the position where the enemy's projectile will be fired from.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -50.0;

	/**
	 * The vertical offset for the position where the enemy's projectile will be fired from.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 22.0;

	/**
	 * The initial health of the second enemy plane.
	 */
	private static final int INITIAL_HEALTH = 1;

	/**
	 * The fire rate for the second enemy plane, representing the probability that the enemy will fire a projectile
	 * in a given frame.
	 */
	private static final double FIRE_RATE = .02;

	/**
	 * Constructs a new SecondEnemyPlane with the specified initial position and health.
	 *
	 * @param initialXPos The initial X position of the enemy plane.
	 * @param initialYPos The initial Y position of the enemy plane.
	 */
	public SecondEnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the second enemy plane. The plane moves horizontally to the left based on the horizontal
	 * velocity defined in the class.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Determines if the second enemy plane fires a projectile in the current frame.
	 * If the random chance is less than the fire rate, the enemy plane fires a projectile.
	 *
	 * @return An instance of {@link EnemyProjectile} if the plane fires a projectile; otherwise, returns null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	/**
	 * Updates the actor's state. This method is responsible for updating the position of the second enemy plane
	 * by calling the updatePosition method.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
