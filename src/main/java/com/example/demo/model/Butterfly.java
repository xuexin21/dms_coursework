package com.example.demo.model;

import com.example.demo.level.LevelParent;
import java.util.Random;

/**
 * The Butterfly class represents a special type of enemy in the game that moves horizontally across the screen.
 * Upon destruction, it grants the player (UserPlane) one of several possible power-ups, such as extra life, increased speed,
 * or increased projectile speed.
 */
public class Butterfly extends FighterPlane {

    /**
     * The image file name representing the butterfly.
     */
    private static final String IMAGE_NAME = "butterfly.png";

    /**
     * The height of the butterfly image in pixels.
     */
    private static final int IMAGE_HEIGHT = 60;

    /**
     * The horizontal velocity at which the butterfly moves across the screen.
     */
    private static final int HORIZONTAL_VELOCITY = -6;

    /**
     * The initial health of the butterfly, which is 1.
     */
    private static final int INITIAL_HEALTH = 1;

    /**
     * A random number used to determine which power-up the butterfly will give to the user upon being destroyed.
     */
    private int number;

    /**
     * The user plane (player) associated with the game, to give power-ups upon butterfly destruction.
     */
    private UserPlane user;

    /**
     * Constructs a new Butterfly instance with specified initial X and Y positions, and initializes its health and random number for power-ups.
     *
     * @param initialXPos The initial X position of the butterfly on the screen.
     * @param initialYPos The initial Y position of the butterfly on the screen.
     * @param health      The health of the butterfly. (This value is fixed to 1.)
     */
    public Butterfly(double initialXPos, double initialYPos, int health) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        Random rand = new Random();
        number = rand.nextInt(3); // Randomly choose a number for power-up determination.
        user = LevelParent.getUser(); // Get the user instance to apply power-ups.
    }

    /**
     * Updates the butterfly's position by moving it horizontally based on its velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the butterfly's state by updating its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * The butterfly does not fire projectiles, so this method returns null.
     *
     * @return null
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return null;
    }

    /**
     * The butterfly takes damage, and if its health reaches 0, it grants the player a power-up based on a random number.
     * The power-up could be one of the following:
     * - Extra life
     * - Increased speed
     * - Increased projectile speed
     */
    @Override
    public void takeDamage() {
        super.takeDamage();

        if (getHealth() == 0) {
            // When the butterfly is destroyed, grant a power-up to the player.
            switch (number) {
                case 0:
                    user.giveExtraLife(); // Give the player an extra life.
                    break;
                case 1:
                    user.increaseSpeed(); // Increase the player's movement speed.
                    break;
                case 2:
                    user.increaseProjectileSpeed(); // Increase the player's projectile speed.
                    break;
            }
        }
    }
}
