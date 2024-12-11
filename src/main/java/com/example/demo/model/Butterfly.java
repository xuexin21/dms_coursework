package com.example.demo.model;

import com.example.demo.level.LevelParent;
import com.example.demo.audio.Sound;
import java.util.Random;

public class Butterfly extends FighterPlane {
    private static final String IMAGE_NAME = "butterfly.png";
    private static final int IMAGE_HEIGHT = 60;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final int INITIAL_HEALTH = 1;
    private int number;
    private Sound sound;
    private UserPlane user;

    public Butterfly(double initialXPos, double initialYPos, int health) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        Random rand = new Random();
        number = rand.nextInt(3);
        user = LevelParent.getUser();
        sound = new Sound();
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
    public ActiveActorDestructible fireProjectile() {
        return null;
    }

    @Override
    public void takeDamage () {
        super.takeDamage();
        if(getHealth() == 0) {
            switch (number) {
                case 0:
                    user.giveExtraLife();
                    sound.playPowerUpSound();
                    break;
                case 1:
                    user.increaseSpeed();
                    sound.playPowerUpSound();
                    break;
                case 2:
                    user.increaseProjectileSpeed();
                    sound.playPowerUpSound();
                    break;
            }
        }
    }
}
