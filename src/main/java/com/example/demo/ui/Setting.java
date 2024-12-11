package com.example.demo.ui;

import com.example.demo.audio.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

public class Setting {

    /**
     * The main menu scene to return to.
     */
    private final Scene mainMenuScene;
    private final Stage stage;
    private final Music music;
    private final Sound sound;

    /**
     * Constructs the Setting pop-up with the main menu scene reference.
     *
     * @param mainMenuScene the main menu scene to return to
     */
    public Setting(Scene mainMenuScene, Music music, Sound sound) {
        this.mainMenuScene = mainMenuScene;
        this.stage = (Stage) mainMenuScene.getWindow();
        this.music = music;
        this.sound = sound;
    }

    /**
     * Displays the settings as a pop-up window.
     */
    public void showPopup() {
        // Create a new Stage for the pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        popupStage.setTitle("Settings");

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

        // Close Button to dismiss the pop-up
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            sound.playButtonSound();
            popupStage.close();
        });
        closeButton.getStyleClass().add("button");

        // Layout Setup
        VBox layout = new VBox(20, titleLabel, soundToggle, musicToggle, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");

        // Scene Creation
        Scene popupScene = new Scene(layout, 400, 300); // Adjust the size of the pop-up
        popupScene.getStylesheets().add(getClass().getResource("/com/example/demo/styles/setting.css").toExternalForm());

        popupStage.setScene(popupScene);
        popupStage.showAndWait(); // Show the pop-up and wait until it is closed
    }

    private void toggleMuteSound(ToggleButton soundToggle){
        if(sound.isMuted()){
            sound.unmute();
            soundToggle.setText("Sound: ON");
        } else {
            sound.mute();
            soundToggle.setText("Sound: OFF");
        }
    }

    private void toggleMuteMusic(ToggleButton musicToggle){
        if(music.isMuted()){
            music.unmute();
            musicToggle.setText("Music: ON");
        } else {
            music.mute();
            musicToggle.setText("Music: OFF");
        }
    }
}
