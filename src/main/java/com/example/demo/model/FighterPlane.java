package com.example.demo.model;

/**
 * The FighterPlane class represents a fighter plane in the game.
 * It is an abstract class that extends from ActiveActorDestructible and
 * defines common behavior for all types of fighter planes, including health management
 * and firing projectiles. Specific fighter plane behaviors are implemented in subclasses.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	protected int health;

	/**
	 * Constructs a FighterPlane with the specified image, initial position, and health.
	 *
	 * @param imageName the name of the image file representing the fighter plane
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial X position of the fighter plane
	 * @param initialYPos the initial Y position of the fighter plane
	 * @param health the initial health of the fighter plane
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane.
	 * This method is abstract and must be implemented by subclasses to specify the projectile behavior.
	 *
	 * @return the projectile fired by the plane, or null if no projectile is fired
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Decreases the health of the fighter plane by 1.
	 * If the health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X position for the projectile based on the plane's current position and an offset.
	 *
	 * @param xPositionOffset the offset to be added to the X position
	 * @return the calculated X position for the projectile
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y position for the projectile based on the plane's current position and an offset.
	 *
	 * @param yPositionOffset the offset to be added to the Y position
	 * @return the calculated Y position for the projectile
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health of the fighter plane has reached zero.
	 *
	 * @return true if the health is zero, false otherwise
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the fighter plane.
	 *
	 * @return the current health of the plane
	 */
	public int getHealth() {
		return health;
	}
}
