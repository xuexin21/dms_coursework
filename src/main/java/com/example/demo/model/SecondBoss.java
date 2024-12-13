package com.example.demo.model;

/**
 * The SecondBoss class represents a more powerful version of the {@link Boss} in the game.
 * It inherits from the Boss class but overrides the fire rate to make the boss fire projectiles
 * more frequently than the original boss.
 */
public class SecondBoss extends Boss {

    /**
     * The initial health of the second boss.
     */
    private static final int INITIAL_HEALTH = 100;

    /**
     * The upgraded fire rate for the second boss, which increases the probability
     * of the boss firing projectiles in each frame.
     */
    private static final double UPGRADE_FIRE_RATE = .08;

    /**
     * Constructs a new SecondBoss with upgraded health and fire rate.
     */
    public SecondBoss() {
        super(INITIAL_HEALTH);
    }

    /**
     * Determines whether the boss fires a projectile in the current frame.
     * Overrides the {@link Boss#bossFiresInCurrentFrame()} method to increase the fire rate.
     *
     * @return true if the boss fires a projectile, false otherwise.
     */
    @Override
    protected boolean bossFiresInCurrentFrame() {
        return Math.random() < UPGRADE_FIRE_RATE;
    }
}