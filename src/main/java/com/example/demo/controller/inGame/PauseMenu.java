package com.example.demo.controller.inGame;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import com.example.demo.controller.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

/**
 * The PauseMenu class represents the pause menu that is shown during the game.
 * It allows the player to resume the game, toggle sound and music settings, or return to the main menu.
 */
public class PauseMenu extends StackPane {

    /**
     * The path to the pause menu's stylesheet.
     */
    private static final String PAUSE_MENU_STYLE = "/com/example/demo/styles/pause.css";

    /**
     * The Music object for controlling background music settings.
     */
    private final Music music;

    /**
     * The Sound object for controlling sound effects settings.
     */
    private final Sound sound;

    /**
     * The action to be performed when the game is resumed.
     */
    private final Runnable onResume;

    /**
     * The action to be performed when returning to the main menu.
     */
    private final Runnable onReturnToMainMenu;

    /**
     * Constructs a PauseMenu object with options to resume the game or return to the main menu.
     * The pause menu also includes sound and music toggle buttons.
     *
     * @param onResume the Runnable to be executed when the game is resumed
     * @param onReturnToMainMenu the Runnable to be executed when returning to the main menu
     * @param music the Music object to control background music
     * @param sound the Sound object to control sound effects
     */
    public PauseMenu(Runnable onResume, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = music;
        this.sound = sound;
        this.onResume = onResume;
        this.onReturnToMainMenu = onReturnToMainMenu;
    }

    /**
     * Displays the pause menu with buttons to resume the game, toggle sound/music, and return to the main menu.
     */
    public void showPauseMenu() {

        Label titleLabel = new Label("SETTINGS");
        titleLabel.getStyleClass().add("title");

        ToggleButton soundToggle = new ToggleButton(sound.isMuted() ? "Sound: OFF" : "Sound: ON");
        soundToggle.setOnAction(e -> toggleMuteSound(soundToggle));
        soundToggle.getStyleClass().add("toggle");

        ToggleButton musicToggle = new ToggleButton(music.isMuted() ? "Music: OFF" : "Music: ON");
        musicToggle.setOnAction(e -> toggleMuteMusic(musicToggle));
        musicToggle.getStyleClass().add("toggle");

        Button resumeButton = new Button("Resume");
        resumeButton.setOnAction(e -> {
            sound.playButtonSound();
            setVisible(false);
            onResume.run();
        });
        resumeButton.getStyleClass().add("button");

        Button returnToMainMenuButton = new Button("Return to Main Menu");
        returnToMainMenuButton.setOnAction(e -> {
            sound.playButtonSound();
            onReturnToMainMenu.run();
        });
        returnToMainMenuButton.getStyleClass().add("button");

        VBox layout = new VBox(20, titleLabel, soundToggle, musicToggle, resumeButton, returnToMainMenuButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");
        layout.getStylesheets().add(getClass().getResource(PAUSE_MENU_STYLE).toExternalForm());
        double WIDTH = Main.SCREEN_WIDTH / 2 - 190;
        double HEIGHT = Main.SCREEN_HEIGHT / 2 - 250;
        this.setPrefSize(400, 250);
        this.setLayoutX(WIDTH);
        this.setLayoutY(HEIGHT);
        this.getChildren().add(layout);
        this.setVisible(false);
        this.setViewOrder(-1);
    }

    /**
     * Toggles the mute state of the sound effects and updates the toggle button text.
     *
     * @param soundToggle the ToggleButton used for sound mute/unmute
     */
    private void toggleMuteSound(ToggleButton soundToggle) {
        if (sound.isMuted()) {
            sound.unmute();
            soundToggle.setText("Sound: ON");
        } else {
            sound.playButtonSound();
            sound.mute();
            soundToggle.setText("Sound: OFF");
        }
    }

    /**
     * Toggles the mute state of the background music and updates the toggle button text.
     *
     * @param musicToggle the ToggleButton used for music mute/unmute
     */
    private void toggleMuteMusic(ToggleButton musicToggle) {
        if (music.isMuted()) {
            music.unmute();
            musicToggle.setText("Music: ON");
        } else {
            sound.playButtonSound();
            music.mute();
            musicToggle.setText("Music: OFF");
        }
    }
}
