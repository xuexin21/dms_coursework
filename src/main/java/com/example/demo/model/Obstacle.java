package com.example.demo.model;

public class Obstacle extends ActiveActorDestructible {

	private static final String IMAGE_NAME = "cloud.png"; // Path to your cloud image
	private static final int IMAGE_HEIGHT = 450;
	private static final int HORIZONTAL_VELOCITY = -3;// Clouds move slower than enemies

	public Obstacle(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos); // Clouds are indestructible
		setViewOrder(-1);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	@Override
	public void takeDamage() {
		// Clouds don't take damage; override to do nothing
	}
}
