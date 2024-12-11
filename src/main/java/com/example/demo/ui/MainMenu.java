package com.example.demo.ui;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.controller.Controller;
import com.example.demo.audio.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MainMenu {
    private final Stage stage;
    private final String BACKGROUND_IMAGE = "/com/example/demo/images/main.jpg";
    private final String MAIN_MENU_STYLE = "/com/example/demo/styles/main_menu.css";
    private final Music music = new Music();
    private final Sound sound = new Sound();

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

        // Create the Options button
        Button settingsButton = new Button("Settings");
        settingsButton.getStyleClass().add("button"); // Apply button style
        settingsButton.setOnAction(e -> {
            sound.playButtonSound();
            openSettings();
        });

        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("button");
        exitButton.setOnAction(e -> {
            sound.playButtonSound();
            stage.close();
        });

        // Set up the layout (VBox)
        VBox layout = new VBox(20);
        layout.getStyleClass().add("layout"); // Apply layout style
        layout.getChildren().addAll(titleLabel, playButton, settingsButton, exitButton);

        Image backgroundImage = new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        layout.setBackground(new Background(bgImage));


        // Create the scene and set it to the stage
        Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());

        // Add the stylesheet to the scene
        scene.getStylesheets().add(getClass().getResource(MAIN_MENU_STYLE).toExternalForm());

        stage.setScene(scene);
        stage.show();
        music.playMainMenuMusic();
    }

    private void openSettings() {
        // Pass the current scene and MediaPlayer to the Setting pop-up
        Setting setting = new Setting(stage.getScene(), music, sound);
        setting.showPopup(); // Call the pop-up method from the Setting class
    }

    private void startLevelOne() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Controller controller = new Controller(stage);
        controller.launchGame();
    }
}
