package com.example.demo.model;

import com.example.demo.level.LevelParent;
import java.util.Random;

public class Butterfly extends FighterPlane {
    private static final String IMAGE_NAME = "butterfly.png";
    private static final int IMAGE_HEIGHT = 80;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final int INITIAL_HEALTH = 1;
    private int number;
    private UserPlane user;

    public Butterfly(double initialXPos, double initialYPos, int health) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        Random rand = new Random();
        number = rand.nextInt(3);
        user = LevelParent.getUser();
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
                    System.out.println("extralife");
                    break;
                case 1:
                    user.increaseSpeed();
                    System.out.println("increaseSpeed");
                    break;
                case 2:
                    user.increaseProjectileSpeed();
                    System.out.println("increaseProjectileSpeed");
                    break;
            }
        }
    }
}
