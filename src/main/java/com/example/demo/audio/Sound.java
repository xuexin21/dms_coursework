package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private static final String BUTTON_SOUND = "/com/example/demo/sounds/button_sound.mp3";
    private static final String ENEMY_PROJECTILE_SOUND = "/com/example/demo/sounds/enemy_projectile.mp3";
    private static final String EXPLOSION_SOUND = "/com/example/demo/sounds/explosion.mp3";
    private static final String POWERUP_SOUND = "/com/example/demo/sounds/powerup.mp3";
    private static final String USER_PROJECTILE_SOUND = "/com/example/demo/sounds/user_projectile.mp3";
    private Media button;
    private Media explosion;
    private Media enemyProjectile;
    private Media powerUp;
    private Media userProjectile;
    private MediaPlayer buttonSound;
    private MediaPlayer explosionSound;
    private MediaPlayer enemyProjectileSound;
    private MediaPlayer powerUpSound;
    private MediaPlayer userProjectileSound;
    private boolean isMuted = false;

    public Sound() {
        button = new Media(getClass().getResource(BUTTON_SOUND).toExternalForm());
        explosion = new Media(getClass().getResource(EXPLOSION_SOUND).toExternalForm());
        enemyProjectile = new Media(getClass().getResource(ENEMY_PROJECTILE_SOUND).toExternalForm());
        powerUp = new Media(getClass().getResource(POWERUP_SOUND).toExternalForm());
        userProjectile = new Media(getClass().getResource(USER_PROJECTILE_SOUND).toExternalForm());

        buttonSound = new MediaPlayer(button);
        explosionSound = new MediaPlayer(explosion);
        enemyProjectileSound = new MediaPlayer(enemyProjectile);
        powerUpSound = new MediaPlayer(powerUp);
        userProjectileSound = new MediaPlayer(userProjectile);
    }

    public void playButtonSound() {
        if (isMuted) return;
        buttonSound.stop();
        buttonSound.play();
    }

    public void playExplosionSound() {
        if (isMuted) return;
        explosionSound.stop();
        explosionSound.play();
        explosionSound.setVolume(70);
    }

    public void playEnemyProjectileSound() {
        if (isMuted) return;
        enemyProjectileSound.stop();
        enemyProjectileSound.play();
        enemyProjectileSound.setVolume(30);
    }

    public void playPowerUpSound() {
        if (isMuted) return;
        powerUpSound.stop();
        powerUpSound.play();
        powerUpSound.setVolume(50);
    }

    public void playUserProjectileSound() {
        if (isMuted) return;
        userProjectileSound.stop();
        userProjectileSound.play();
        userProjectileSound.setVolume(30);
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void mute() {
        isMuted = true;
        buttonSound.pause();
    }

    public void unmute() {
        isMuted = false;
        buttonSound.play();
    }
}
