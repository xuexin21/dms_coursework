package com.example.demo.model;

public class SecondBoss extends Boss {

    private static final int INITIAL_HEALTH = 100;
    private static final double UPGRADE_FIRE_RATE = .08;

    public SecondBoss() {
        super(INITIAL_HEALTH);
    }

    @Override
    protected boolean bossFiresInCurrentFrame() {
        return Math.random() < UPGRADE_FIRE_RATE;
    }
}
