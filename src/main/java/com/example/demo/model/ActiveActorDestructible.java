package com.example.demo.model;

/**
 * Represents an active actor that can be destroyed in the game.
 * This class extends {@link ActiveActor} and implements the {@link Destructible} interface to provide functionality for destruction and damage-taking.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	/**
	 * Constructs a new ActiveActorDestructible with the specified image, height, and initial position.
	 * The actor starts in a non-destroyed state.
	 *
	 * @param imageName   The name of the image file to be used for the actor.
	 * @param imageHeight The height of the actor's image.
	 * @param initialXPos The initial X position of the actor.
	 * @param initialYPos The initial Y position of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor. This method is abstract and must be implemented by subclasses
	 * to define how the actor's position is updated, considering the destructible nature of the actor.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the actor for each frame. This method is abstract and must be implemented by subclasses
	 * to handle updates such as movement, animation, or changes in state (like destruction).
	 */
	public abstract void updateActor();

	/**
	 * Causes the actor to take damage. This method must be implemented by subclasses to define how damage
	 * is applied to the actor, potentially resulting in destruction.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor, setting it as destroyed and stopping its actions in the game.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed state of the actor.
	 *
	 * @param isDestroyed Whether the actor should be marked as destroyed.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks if the actor is destroyed.
	 *
	 * @return {@code true} if the actor is destroyed, {@code false} otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
