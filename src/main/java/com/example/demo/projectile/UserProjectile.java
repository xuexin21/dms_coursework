package com.example.demo.projectile;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 12;
	public static int HORIZONTAL_VELOCITY = 15;

	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}

	public static void increaseProjectileVelocity() {
		if (HORIZONTAL_VELOCITY < 30) {
			HORIZONTAL_VELOCITY += 3;
		}
	}

	public static void resetProjectileVelocity() {
		HORIZONTAL_VELOCITY = 15;
	}

	public static int getProjectileSpeed() {
		return HORIZONTAL_VELOCITY;
	}
}
