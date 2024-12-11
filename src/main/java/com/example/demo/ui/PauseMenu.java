package com.example.demo.ui;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PauseMenu {

    private static final String PAUSE_MENU_STYLE = "/com/example/demo/styles/setting.css";
    private final Music music;
    private final Sound sound;
    private final Runnable onResume;
    private final Runnable onReturnToMainMenu;

    public PauseMenu(Runnable onResume, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = new Music();
        this.sound = new Sound();
        this.onResume = onResume;
        this.onReturnToMainMenu = onReturnToMainMenu;
    }

    public void showPauseMenu() {
        Stage popUpMenu = new Stage();
        popUpMenu.initModality(Modality.APPLICATION_MODAL); // Make it modal
        popUpMenu.setTitle("Menu");

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
            System.out.println("Resume button clicked");
            onResume.run();
            popUpMenu.close();
        });
        resumeButton.getStyleClass().add("button");

        Button returnToMainMenuButton = new Button("Return to Main Menu");
        returnToMainMenuButton.setOnAction(e -> {
            sound.playButtonSound();
            onReturnToMainMenu.run();
            popUpMenu.close();
        });
        returnToMainMenuButton.getStyleClass().add("button");

        // Layout Setup
        VBox layout = new VBox(20, titleLabel, soundToggle, musicToggle, resumeButton, returnToMainMenuButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");

        Scene scene = new Scene(layout, 300, 400); // Adjust width/height as needed
        scene.getStylesheets().add(getClass().getResource(PAUSE_MENU_STYLE).toExternalForm());

        popUpMenu.setScene(scene);
        popUpMenu.showAndWait(); // Show the pop-up and wait until it is closed
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
