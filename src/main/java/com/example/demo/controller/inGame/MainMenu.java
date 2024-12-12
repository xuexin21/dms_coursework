package com.example.demo.controller.inGame;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.audio.*;
import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MainMenu {
    private final Stage stage;
    private final String BACKGROUND_VIDEO = "/com/example/demo/images/main.mp4"; // Path to video file
    private final String MAIN_MENU_STYLE = "/com/example/demo/styles/main_menu.css";
    private final Music music = new Music();
    private final Sound sound = new Sound();
    private MediaPlayer backgroundPlayer;

    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    public void display() {
        // Create the "SKY BATTLE" label
        Label titleLabel = new Label("SKY BATTLE");
        titleLabel.getStyleClass().add("title-label"); // Apply title label style

        // Create the Play button
        Button playButton = new Button("Play");
        playButton.getStyleClass().add("button"); // Apply button style
        playButton.setOnAction(e -> {
            sound.playButtonSound();
            try {
                startLevelOne();
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Sound Toggle
        ToggleButton soundToggle = new ToggleButton(sound.isMuted() ? "Sound: OFF" : "Sound: ON");
        soundToggle.setOnAction(e -> toggleMuteSound(soundToggle));
        soundToggle.getStyleClass().add("toggle");

        // Music Toggle
        ToggleButton musicToggle = new ToggleButton(music.isMuted() ? "Music: OFF" : "Music: ON");
        musicToggle.setOnAction(e -> toggleMuteMusic(musicToggle));
        musicToggle.getStyleClass().add("toggle");

        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("button");
        exitButton.setOnAction(e -> {
            sound.playButtonSound();
            stage.close();
        });

        // Set up the layout (VBox)
        VBox layout = new VBox(20);
        layout.getStyleClass().add("layout"); // Apply layout style
        layout.getChildren().addAll(titleLabel, playButton, soundToggle, musicToggle, exitButton);

        // Set up the background video
        Media video = new Media(getClass().getResource(BACKGROUND_VIDEO).toExternalForm());
        backgroundPlayer = new MediaPlayer(video);
        backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the video
        backgroundPlayer.setVolume(0); // Mute the video
        backgroundPlayer.play();

        // Create a MediaView to display the video
        MediaView mediaView = new MediaView(backgroundPlayer);
        mediaView.setFitWidth(stage.getWidth()); // Set the width to match the stage
        mediaView.setFitHeight(stage.getHeight()); // Set the height to match the stage

        // Use a StackPane to stack the video behind the UI elements (layout)
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(mediaView); // Add video at the bottom
        stackPane.getChildren().add(layout); // Add UI elements (buttons, etc.) on top

        // Create the scene and set it to the stage
        Scene scene = new Scene(stackPane, stage.getWidth(), stage.getHeight());

        // Add the stylesheet to the scene
        scene.getStylesheets().add(getClass().getResource(MAIN_MENU_STYLE).toExternalForm());

        stage.setScene(scene);
        stage.show();
        music.playMainMenuMusic();
    }

    private void startLevelOne() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Controller controller = new Controller(stage, music, sound);
        controller.launchGame();
        music.stopMainMenuMusic();
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
