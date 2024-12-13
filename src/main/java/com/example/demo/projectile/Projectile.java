package com.example.demo.projectile;

import com.example.demo.model.ActiveActorDestructible;

/**
 * The Projectile class represents a generic projectile in the game. It is an abstract class that extends {@link ActiveActorDestructible}.
 * This class provides common functionality for projectiles fired by different actors (e.g., enemy, user, boss) in the game.
 * Subclasses of this class must implement the {@link #updatePosition()} method to define how the projectile moves.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a new Projectile with the specified image name, image height, initial X position, and initial Y position.
	 *
	 * @param imageName     The name of the image representing the projectile.
	 * @param imageHeight   The height of the image representing the projectile.
	 * @param initialXPos   The initial X position of the projectile on the screen.
	 * @param initialYPos   The initial Y position of the projectile on the screen.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Takes damage and destroys the projectile. This method is invoked when the projectile is hit by something.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Abstract method to update the position of the projectile. Subclasses must implement this method
	 * to define how the projectile moves on the screen.
	 */
	@Override
	public abstract void updatePosition();
}