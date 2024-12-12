package com.example.demo.ui;

import com.example.demo.controller.Main;
import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class PauseMenu extends StackPane {

    private static final String PAUSE_MENU_STYLE = "/com/example/demo/styles/pause.css";
    private final Music music;
    private final Sound sound;
    private final Runnable onResume;
    private final Runnable onReturnToMainMenu;

    public PauseMenu(Runnable onResume, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = music;
        this.sound = sound;
        this.onResume = onResume;
        this.onReturnToMainMenu = onReturnToMainMenu;
    }

    public void showPauseMenu() {
        // Title
        Label titleLabel = new Label("SETTINGS");
        titleLabel.getStyleClass().add("title");

        // Sound Toggle
        ToggleButton soundToggle = new ToggleButton(sound.isMuted() ? "Sound: OFF" : "Sound: ON");
        soundToggle.setOnAction(e -> toggleMuteSound(soundToggle));
        soundToggle.getStyleClass().add("toggle");

        // Music Toggle
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

        // Layout Setup
        VBox layout = new VBox(20, titleLabel, soundToggle, musicToggle, resumeButton, returnToMainMenuButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");
        layout.getStylesheets().add(getClass().getResource(PAUSE_MENU_STYLE).toExternalForm());
        double WIDTH = Main.SCREEN_WIDTH/2 - 150;
        double HEIGHT = Main.SCREEN_HEIGHT/2 - 225;
        this.setPrefSize(300, 250);
        this.setLayoutX(WIDTH);
        this.setLayoutY(HEIGHT);
        this.getChildren().add(layout);
        this.setVisible(false);
    }

    private void toggleMuteSound(ToggleButton soundToggle){
        if(sound.isMuted()){
            sound.unmute();
            soundToggle.setText("Sound: ON");
        } else {
            sound.playButtonSound();
            sound.mute();
            soundToggle.setText("Sound: OFF");
        }
    }

    private void toggleMuteMusic(ToggleButton musicToggle){
        if(music.isMuted()){
            music.unmute();
            musicToggle.setText("Music: ON");
        } else {
            sound.playButtonSound();
            music.mute();
            musicToggle.setText("Music: OFF");
        }
    }
}
