package com.example.demo.model;

public class SecondBoss extends Boss {

    private static final int INITIAL_HEALTH = 150;
    private static final double UPGRADE_FIRE_RATE = .06;

    public SecondBoss() {
        super(INITIAL_HEALTH);
    }

    @Override
    protected boolean bossFiresInCurrentFrame() {
        return Math.random() < UPGRADE_FIRE_RATE;
    }
}
