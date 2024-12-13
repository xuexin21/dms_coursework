package com.example.demo.controller.inGame;

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
import java.lang.reflect.InvocationTargetException;

/**
 * The MainMenu class represents the main menu screen of the game.
 * It provides options to play the game, toggle sound and music, and exit the game.
 * Additionally, it displays a background video and applies custom styles for the UI components.
 */
public class MainMenu {

    /**
     * The path to the background video file.
     */
    private final String BACKGROUND_VIDEO = "/com/example/demo/images/main.mp4"; // Path to video file

    /**
     * The path to the CSS stylesheet for the main menu.
     */
    private final String MAIN_MENU_STYLE = "/com/example/demo/styles/main_menu.css";

    /**
     * The Music object for managing the background music.
     */
    private final Music music = new Music();

    /**
     * The Sound object for managing sound effects.
     */
    private final Sound sound = new Sound();

    /**
     * The MediaPlayer for playing the background video.
     */
    private MediaPlayer backgroundPlayer;

    /**
     * The Stage where the main menu is displayed.
     */
    private final Stage stage;

    /**
     * Constructs a MainMenu object with the specified stage for displaying the menu.
     *
     * @param stage the primary stage to display the main menu
     */
    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    /**
     * Displays the main menu with options to play the game, toggle sound and music,
     * and exit the game. The background video is also played in a loop, and the
     * menu layout is customized using a CSS stylesheet.
     */
    public void display() {

        Label titleLabel = new Label("SKY BATTLE");
        titleLabel.getStyleClass().add("title-label");

        Button playButton = new Button("Play");
        playButton.getStyleClass().add("button");
        playButton.setOnAction(e -> {
            sound.playButtonSound();
            try {
                startLevelOne();
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });

        ToggleButton soundToggle = new ToggleButton(sound.isMuted() ? "Sound: OFF" : "Sound: ON");
        soundToggle.setOnAction(e -> toggleMuteSound(soundToggle));
        soundToggle.getStyleClass().add("toggle");

        ToggleButton musicToggle = new ToggleButton(music.isMuted() ? "Music: OFF" : "Music: ON");
        musicToggle.setOnAction(e -> toggleMuteMusic(musicToggle));
        musicToggle.getStyleClass().add("toggle");

        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("button");
        exitButton.setOnAction(e -> {
            sound.playButtonSound();
            stage.close();
        });

        VBox layout = new VBox(20);
        layout.getStyleClass().add("layout");
        layout.getChildren().addAll(titleLabel, playButton, soundToggle, musicToggle, exitButton);

        Media video = new Media(getClass().getResource(BACKGROUND_VIDEO).toExternalForm());
        backgroundPlayer = new MediaPlayer(video);
        backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundPlayer.setVolume(0);
        backgroundPlayer.play();

        MediaView mediaView = new MediaView(backgroundPlayer);
        mediaView.setFitWidth(stage.getWidth());
        mediaView.setFitHeight(stage.getHeight());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(mediaView);
        stackPane.getChildren().add(layout);

        Scene scene = new Scene(stackPane, stage.getWidth(), stage.getHeight());
        scene.getStylesheets().add(getClass().getResource(MAIN_MENU_STYLE).toExternalForm());

        stage.setScene(scene);
        stage.show();
        music.playMainMenuMusic();
    }

    /**
     * Starts the first level of the game by creating a Controller and launching the game.
     * Stops the main menu music once the game starts.
     *
     * @throws ClassNotFoundException if the class for the level cannot be found
     * @throws InvocationTargetException if an exception occurs during the invocation of the constructor
     * @throws NoSuchMethodException if the method to instantiate the level cannot be found
     * @throws InstantiationException if the level cannot be instantiated
     * @throws IllegalAccessException if there is an illegal access during instantiation
     */
    private void startLevelOne() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Controller controller = new Controller(stage, music, sound);
        controller.launchGame();  // Launch the game
        music.stopMainMenuMusic();  // Stop the main menu music
    }

    /**
     * Toggles the mute state of sound. If the sound is muted, it unmutes the sound,
     * and updates the toggle button text to indicate the current state.
     *
     * @param soundToggle the toggle button that controls the sound
     */
    private void toggleMuteSound(ToggleButton soundToggle){
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
     * Toggles the mute state of music. If the music is muted, it unmutes the music,
     * and updates the toggle button text to indicate the current state.
     *
     * @param musicToggle the toggle button that controls the music
     */
    private void toggleMuteMusic(ToggleButton musicToggle){
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
