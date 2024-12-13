package com.example.demo.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The Sound class is responsible for handling sound effects throughout the game.
 * It provides methods to play various game-related sound effects (e.g., button clicks,
 * explosions, game start, game over, power-ups, etc.). It also supports muting
 * and unmuting the sound effects.
 * <p>
 * The sound effects can be played, paused, and stopped. Each sound effect is managed
 * by a MediaPlayer instance and can be played with different volume levels. The class
 * also ensures that no sound is played when the sound is muted.
 */
public class Sound {

    /**
     * The path to the button sound file.
     */
    private static final String BUTTON_SOUND = "/com/example/demo/sounds/button_sound.mp3";

    /**
     * The path to the enemy projectile sound file.
     */
    private static final String ENEMY_PROJECTILE_SOUND = "/com/example/demo/sounds/enemy_projectile.mp3";

    /**
     * The path to the explosion sound file.
     */
    private static final String EXPLOSION_SOUND = "/com/example/demo/sounds/explosion.mp3";

    /**
     * The path to the game over sound file.
     */
    private static final String GAME_OVER_SOUND = "/com/example/demo/sounds/gameover.mp3";

    /**
     * The path to the game start sound file.
     */
    private static final String GAME_START_SOUND = "/com/example/demo/sounds/gamestart.mp3";

    /**
     * The path to the next level sound file.
     */
    private static final String NEXT_LEVEL_SOUND = "/com/example/demo/sounds/next_level.mp3";

    /**
     * The path to the no shield sound file.
     */
    private static final String NO_SHIELD_SOUND = "/com/example/demo/sounds/no_shield.mp3";

    /**
     * The path to the power up sound file.
     */
    private static final String POWER_UP_SOUND = "/com/example/demo/sounds/powerup.mp3";

    /**
     * The path to the shield sound file.
     */
    private static final String SHIELD_SOUND = "/com/example/demo/sounds/shield.mp3";

    /**
     * The path to the win sound file.
     */
    private static final String WIN_SOUND = "/com/example/demo/sounds/win.mp3";

    /**
     * The path to the user projectile sound file.
     */
    private static final String USER_PROJECTILE_SOUND = "/com/example/demo/sounds/user_projectile.mp3";

    /**
     * The Media object for the button click sound effect.
     * This sound is played when the player clicks a button.
     */
    private final Media button;

    /**
     * The Media object for the explosion sound effect.
     * This sound is played when an explosion occurs in the game.
     */
    private final Media explosion;

    /**
     * The Media object for the enemy projectile sound effect.
     * This sound is played when an enemy launches a projectile.
     */
    private final Media enemyProjectile;

    /**
     * The Media object for the game over sound effect.
     * This sound is played when the game ends.
     */
    private final Media gameover;

    /**
     * The Media object for the game start sound effect.
     * This sound is played when the game starts.
     */
    private final Media gamestart;

    /**
     * The Media object for the next level sound effect.
     * This sound is played when the player advances to the next level.
     */
    private final Media nextLevel;

    /**
     * The Media object for the no shield sound effect.
     * This sound is played when the player loses their shield.
     */
    private final Media noShield;

    /**
     * The Media object for the power-up sound effect.
     * This sound is played when the player collects a power-up.
     */
    private final Media powerUp;

    /**
     * The Media object for the shield sound effect.
     * This sound is played when the player activates or gains a shield.
     */
    private final Media shield;

    /**
     * The Media object for the win sound effect.
     * This sound is played when the player wins the game.
     */
    private final Media win;

    /**
     * The Media object for the user projectile sound effect.
     * This sound is played when the player fires a projectile.
     */
    private final Media userProjectile;

    /**
     * The MediaPlayer object for the button click sound effect.
     * This object is used to play the sound when the player clicks a button.
     */
    private final MediaPlayer buttonSound;

    /**
     * The MediaPlayer object for the explosion sound effect.
     * This object is used to play the sound when an explosion occurs in the game.
     */
    private final MediaPlayer explosionSound;

    /**
     * The MediaPlayer object for the game over sound effect.
     * This object is used to play the sound when the game ends.
     */
    private final MediaPlayer gameoverSound;

    /**
     * The MediaPlayer object for the game start sound effect.
     * This object is used to play the sound when the game starts.
     */
    private final MediaPlayer gameStartSound;

    /**
     * The MediaPlayer object for the next level sound effect.
     * This object is used to play the sound when the player advances to the next level.
     */
    private final MediaPlayer nextLevelSound;

    /**
     * The MediaPlayer object for the no shield sound effect.
     * This object is used to play the sound when the player loses their shield.
     */
    private final MediaPlayer noShieldSound;

    /**
     * The MediaPlayer object for the enemy projectile sound effect.
     * This object is used to play the sound when an enemy launches a projectile.
     */
    private final MediaPlayer enemyProjectileSound;

    /**
     * The MediaPlayer object for the power-up sound effect.
     * This object is used to play the sound when the player collects a power-up.
     */
    private final MediaPlayer powerUpSound;

    /**
     * The MediaPlayer object for the shield sound effect.
     * This object is used to play the sound when the player activates or gains a shield.
     */
    private final MediaPlayer shieldSound;

    /**
     * The MediaPlayer object for the win sound effect.
     * This object is used to play the sound when the player wins the game.
     */
    private final MediaPlayer winSound;

    /**
     * The MediaPlayer object for the user projectile sound effect.
     * This object is used to play the sound when the player fires a projectile.
     */
    private final MediaPlayer userProjectileSound;

    /**
     * A boolean flag indicating whether the sound effects are muted.
     * When true, no sound effects will be played. When false, sound effects can be played.
     */
    private boolean isMuted = false;

    /**
     * Constructs a new Sound object. Initializes the Media objects with the
     * corresponding sound file paths and creates MediaPlayer instances for
     * each sound effect.
     */
    public Sound() {
        button = new Media(getClass().getResource(BUTTON_SOUND).toExternalForm());
        explosion = new Media(getClass().getResource(EXPLOSION_SOUND).toExternalForm());
        enemyProjectile = new Media(getClass().getResource(ENEMY_PROJECTILE_SOUND).toExternalForm());
        gameover = new Media(getClass().getResource(GAME_OVER_SOUND).toExternalForm());
        gamestart = new Media(getClass().getResource(GAME_START_SOUND).toExternalForm());
        nextLevel = new Media(getClass().getResource(NEXT_LEVEL_SOUND).toExternalForm());
        noShield = new Media(getClass().getResource(NO_SHIELD_SOUND).toExternalForm());
        powerUp = new Media(getClass().getResource(POWER_UP_SOUND).toExternalForm());
        shield = new Media(getClass().getResource(SHIELD_SOUND).toExternalForm());
        win = new Media(getClass().getResource(WIN_SOUND).toExternalForm());
        userProjectile = new Media(getClass().getResource(USER_PROJECTILE_SOUND).toExternalForm());

        buttonSound = new MediaPlayer(button);
        explosionSound = new MediaPlayer(explosion);
        gameoverSound = new MediaPlayer(gameover);
        gameStartSound = new MediaPlayer(gamestart);
        nextLevelSound = new MediaPlayer(nextLevel);
        noShieldSound = new MediaPlayer(noShield);
        enemyProjectileSound = new MediaPlayer(enemyProjectile);
        powerUpSound = new MediaPlayer(powerUp);
        shieldSound = new MediaPlayer(shield);
        winSound = new MediaPlayer(win);
        userProjectileSound = new MediaPlayer(userProjectile);
    }

    /**
     * Plays the button sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at full volume.
     */
    public void playButtonSound() {
        if (isMuted) return;
        buttonSound.stop();
        buttonSound.play();
        buttonSound.setVolume(1.0);
    }

    /**
     * Plays the explosion sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at 90% volume.
     */
    public void playExplosionSound() {
        if (isMuted) return;
        explosionSound.stop();
        explosionSound.play();
        explosionSound.setVolume(0.9);
    }

    /**
     * Plays the enemy projectile sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at 80% volume.
     */
    public void playEnemyProjectileSound() {
        if (isMuted) return;
        enemyProjectileSound.stop();
        enemyProjectileSound.play();
        enemyProjectileSound.setVolume(0.8);
    }

    /**
     * Plays the game over sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at full volume.
     */
    public void playGameOverSound() {
        if (isMuted) return;
        gameoverSound.stop();
        gameoverSound.play();
        gameoverSound.setVolume(1.0);
    }

    /**
     * Stops the game over sound effect if it is currently playing.
     */
    public void stopGameOverSound() {
        gameoverSound.stop();
    }

    /**
     * Plays the game start sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at full volume.
     */
    public void playGameStartSound() {
        if (isMuted) return;
        gameoverSound.stop();
        gameStartSound.play();
        gameStartSound.setVolume(1.0);
    }

    /**
     * Plays the next level sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at full volume.
     */
    public void playNextLevelSound() {
        if (isMuted) return;
        nextLevelSound.stop();
        nextLevelSound.play();
        nextLevelSound.setVolume(1.0);
    }

    /**
     * Plays the no shield sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at full volume.
     */
    public void playNoShieldSound() {
        if (isMuted) return;
        noShieldSound.stop();
        noShieldSound.play();
        noShieldSound.setVolume(1.0);
    }

    /**
     * Plays the power-up sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at full volume.
     */
    public void playPowerUpSound() {
        if (isMuted) return;
        powerUpSound.stop();
        powerUpSound.play();
        powerUpSound.setVolume(1.0);
    }

    /**
     * Plays the shield sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at full volume.
     */
    public void playShieldSound() {
        if (isMuted) return;
        shieldSound.stop();
        shieldSound.play();
        shieldSound.setVolume(1.0);
    }

    /**
     * Plays the win sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at full volume.
     */
    public void playWinSound() {
        if (isMuted) return;
        winSound.stop();
        winSound.play();
        winSound.setVolume(1.0);
    }

    /**
     * Stops the win sound effect if it is currently playing.
     */
    public void stopWinSound() {
        winSound.stop();
    }

    /**
     * Plays the user projectile sound effect if it is not muted. The sound will stop if it is already playing,
     * and then restart from the beginning at 70% volume.
     */
    public void playUserProjectileSound() {
        if (isMuted) return;
        userProjectileSound.stop();
        userProjectileSound.play();
        userProjectileSound.setVolume(0.7);
    }

    /**
     * Returns whether the sound effects are currently muted.
     *
     * @return true if the sound is muted, false otherwise.
     */
    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Mutes all sound effects. The sound effects will stop, and any currently playing sounds will be paused.
     */
    public void mute() {
        isMuted = true;
        buttonSound.pause();
    }

    /**
     * Unmutes all sound effects. The sound effects will resume playing if not muted.
     */
    public void unmute() {
        isMuted = false;
        buttonSound.play();
    }
}
