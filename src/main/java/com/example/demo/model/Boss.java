package com.example.demo.model;

import com.example.demo.controller.Main;
import com.example.demo.projectile.BossProjectile;

import java.util.*;

/**
 * Represents the boss enemy in the game. The Boss extends {@link FighterPlane} and features special behaviors such as
 * moving in a predefined pattern, firing projectiles, and activating a shield at random intervals.
 */
public class Boss extends FighterPlane {

	/**
	 * The image file name for the boss in the game.
	 */
	private static final String IMAGE_NAME = "bossplane.png";

	/**
	 * The initial X position of the boss on the screen, relative to the screen width.
	 */
	private static final double INITIAL_X_POSITION = (double) Main.SCREEN_WIDTH - 400;

	/**
	 * The initial Y position of the boss on the screen, relative to the screen height.
	 */
	private static final double INITIAL_Y_POSITION = (double) Main.SCREEN_HEIGHT - 350;

	/**
	 * The vertical offset for the projectile when fired by the boss.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 60.0;

	/**
	 * The fire rate of the boss, which determines the probability that the boss will fire a projectile in each frame.
	 */
	private static final double BOSS_FIRE_RATE = .04;

	/**
	 * The probability that the boss will activate its shield in a given frame.
	 */
	private static final double BOSS_SHIELD_PROBABILITY = .002;

	/**
	 * The height of the boss image.
	 */
	private static final int IMAGE_HEIGHT = 80;

	/**
	 * The vertical velocity of the boss, determining the amount of vertical movement per cycle.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The number of movements in a cycle that the boss performs before the move pattern is shuffled.
	 */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/**
	 * A constant used to represent no vertical movement (a zero value) in the move pattern.
	 */
	private static final int ZERO = 0;

	/**
	 * The maximum number of consecutive frames the boss can move in the same direction before shuffling the move pattern.
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/**
	 * The upper bound Y position of the boss (top of the screen).
	 */
	private static final int Y_POSITION_UPPER_BOUND = -5;

	/**
	 * The lower bound Y position of the boss (bottom of the screen).
	 */
	private static final int Y_POSITION_LOWER_BOUND = 610;

	/**
	 * The maximum number of frames the boss's shield can remain activated.
	 */
	private static final int MAX_FRAMES_WITH_SHIELD = 200;

	/**
	 * A list that holds the movement pattern of the boss, consisting of vertical movements (up, down, or stationary).
	 */
	private final List<Integer> movePattern;

	/**
	 * A flag indicating whether the boss is currently shielded.
	 */
	private boolean isShielded;

	/**
	 * A counter for the number of consecutive frames the boss has moved in the same direction.
	 */
	private int consecutiveMovesInSameDirection;

	/**
	 * The index of the current move in the movement pattern list.
	 */
	private int indexOfCurrentMove;

	/**
	 * A counter for the number of frames the boss's shield has been activated.
	 */
	private int framesWithShieldActivated;


	/**
	 * Constructs a new Boss with the specified health, setting the initial position and movement pattern.
	 *
	 * @param health The initial health of the boss.
	 */
	public Boss(int health) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, health);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		initializeMovePattern();
	}

	/**
	 * Updates the position of the boss. The boss moves vertically based on a predefined movement pattern.
	 * If the boss reaches the upper or lower bounds of the screen, its position is reset.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss by calling {@link #updatePosition()} and managing the shield state.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile if the boss is set to fire in the current frame.
	 *
	 * @return A new {@link BossProjectile} if the boss fires, or {@code null} if it does not.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Takes damage unless the boss is shielded. If the boss is shielded, no damage is taken.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the movement pattern of the boss, alternating between moving up, down, and staying still.
	 * The pattern is randomized to create more dynamic movement.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield state of the boss. The shield is activated randomly based on probability,
	 * and it lasts for a predefined number of frames before being deactivated.
	 */
	private void updateShield() {
		if (isShielded) framesWithShieldActivated++;
		else if (shieldShouldBeActivated()) activateShield();
		if (shieldExhausted()) deactivateShield();
	}

	/**
	 * Returns the next move for the boss in the movement pattern. The move pattern is shuffled periodically
	 * after a certain number of consecutive moves in the same direction.
	 *
	 * @return The next vertical movement value (positive, negative, or zero).
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines whether the boss will fire a projectile in the current frame.
	 *
	 * @return {@code true} if the boss fires a projectile, otherwise {@code false}.
	 */
	protected boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Calculates the initial position for the projectile to be fired by the boss.
	 *
	 * @return The initial Y position for the projectile.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines whether the boss's shield should be activated based on a probability.
	 *
	 * @return {@code true} if the shield should be activated, otherwise {@code false}.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Determines whether the boss's shield has been active for the maximum number of frames.
	 *
	 * @return {@code true} if the shield is exhausted, otherwise {@code false}.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the shield, making the boss invulnerable to damage for a short period.
	 */
	private void activateShield() {
		isShielded = true;
	}

	/**
	 * Deactivates the shield, allowing the boss to take damage again.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}

	/**
	 * Returns whether the boss is currently shielded.
	 *
	 * @return {@code true} if the boss is shielded, otherwise {@code false}.
	 */
	public boolean isShielded() {
		return isShielded;
	}
}
