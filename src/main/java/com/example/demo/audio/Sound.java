package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private static final String BUTTON_SOUND = "/com/example/demo/sounds/button_sound.mp3";
    private static final String ENEMY_PROJECTILE_SOUND = "/com/example/demo/sounds/enemy_projectile.mp3";
    private static final String EXPLOSION_SOUND = "/com/example/demo/sounds/explosion.mp3";
    private static final String GAME_OVER_SOUND = "/com/example/demo/sounds/gameover.mp3";
    private static final String NEXT_LEVEL_SOUND = "/com/example/demo/sounds/next_level.mp3";
    private static final String NO_SHIELD_SOUND = "/com/example/demo/sounds/no_shield.mp3";
    private static final String POWERUP_SOUND = "/com/example/demo/sounds/powerup.mp3";
    private static final String SHIELD_SOUND = "/com/example/demo/sounds/shield.mp3";
    private static final String WIN_SOUND = "/com/example/demo/sounds/win.mp3";
    private static final String USER_PROJECTILE_SOUND = "/com/example/demo/sounds/user_projectile.mp3";

    private Media button;
    private Media explosion;
    private Media enemyProjectile;
    private Media gameover;
    private Media nextLevel;
    private Media noShield;
    private Media powerUp;
    private Media shield;
    private Media win;
    private Media userProjectile;

    private MediaPlayer buttonSound;
    private MediaPlayer explosionSound;
    private MediaPlayer gameoverSound;
    private MediaPlayer nextLevelSound;
    private MediaPlayer noShieldSound;
    private MediaPlayer enemyProjectileSound;
    private MediaPlayer powerUpSound;
    private MediaPlayer shieldSound;
    private MediaPlayer winSound;
    private MediaPlayer userProjectileSound;

    private boolean isMuted = false;

    public Sound() {
        button = new Media(getClass().getResource(BUTTON_SOUND).toExternalForm());
        explosion = new Media(getClass().getResource(EXPLOSION_SOUND).toExternalForm());
        enemyProjectile = new Media(getClass().getResource(ENEMY_PROJECTILE_SOUND).toExternalForm());
        gameover = new Media(getClass().getResource(GAME_OVER_SOUND).toExternalForm());
        nextLevel = new Media(getClass().getResource(NEXT_LEVEL_SOUND).toExternalForm());
        noShield = new Media(getClass().getResource(NO_SHIELD_SOUND).toExternalForm());
        powerUp = new Media(getClass().getResource(POWERUP_SOUND).toExternalForm());
        shield = new Media(getClass().getResource(SHIELD_SOUND).toExternalForm());
        win = new Media(getClass().getResource(WIN_SOUND).toExternalForm());
        userProjectile = new Media(getClass().getResource(USER_PROJECTILE_SOUND).toExternalForm());

        buttonSound = new MediaPlayer(button);
        explosionSound = new MediaPlayer(explosion);
        gameoverSound = new MediaPlayer(gameover);
        nextLevelSound = new MediaPlayer(nextLevel);
        noShieldSound = new MediaPlayer(noShield);
        enemyProjectileSound = new MediaPlayer(enemyProjectile);
        powerUpSound = new MediaPlayer(powerUp);
        shieldSound = new MediaPlayer(shield);
        winSound = new MediaPlayer(win);
        userProjectileSound = new MediaPlayer(userProjectile);
    }

    public void playButtonSound() {
        if (isMuted) return;
        buttonSound.stop();
        buttonSound.play();
        buttonSound.setVolume(1.0);
    }

    public void playExplosionSound() {
        if (isMuted) return;
        explosionSound.stop();
        explosionSound.play();
        explosionSound.setVolume(0.9);
    }

    public void playEnemyProjectileSound() {
        if (isMuted) return;
        enemyProjectileSound.stop();
        enemyProjectileSound.play();
        enemyProjectileSound.setVolume(0.8);
    }

    public void playGameOverSound() {
        if (isMuted) return;
        gameoverSound.stop();
        gameoverSound.play();
        gameoverSound.setVolume(1.0);
    }

    public void playNextLevelSound() {
        if (isMuted) return;
        nextLevelSound.stop();
        nextLevelSound.play();
        nextLevelSound.setVolume(1.0);
    }

    public void playNoShieldSound() {
        if (isMuted) return;
        noShieldSound.stop();
        noShieldSound.play();
        noShieldSound.setVolume(1.0);
    }

    public void playPowerUpSound() {
        if (isMuted) return;
        powerUpSound.stop();
        powerUpSound.play();
        powerUpSound.setVolume(1.0);
    }

    public void playShieldSound() {
        if (isMuted) return;
        shieldSound.stop();
        shieldSound.play();
        shieldSound.setVolume(1.0);
    }

    public void playWinSound() {
        if (isMuted) return;
        winSound.stop();
        winSound.play();
        winSound.setVolume(1.0);
    }

    public void playUserProjectileSound() {
        if (isMuted) return;
        userProjectileSound.stop();
        userProjectileSound.play();
        userProjectileSound.setVolume(0.7);
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
