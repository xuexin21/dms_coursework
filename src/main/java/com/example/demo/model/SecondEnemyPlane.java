package com.example.demo.model;

import com.example.demo.projectile.EnemyProjectile;

public class SecondEnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane2.png";
	private static final int IMAGE_HEIGHT = 80;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -50.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 22.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .02;
	private boolean dropsPowerUp;

	public SecondEnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		this.dropsPowerUp = Math.random() < 0.5;
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	public boolean isDropsPowerUp() {
		return dropsPowerUp;
	}

}
