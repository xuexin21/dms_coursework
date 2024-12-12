package com.example.demo.ui;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class ContinueMenu extends StackPane {

    private static final String CONTINUE_MENU_STYLE = "/com/example/demo/styles/continue.css";
    private final Music music;
    private final Sound sound;
    private final Runnable onExit;
    private final Runnable onReturnToMainMenu;

    public ContinueMenu(Runnable onExit, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = music;
        this.sound = sound;
        this.onExit = onExit;
        this.onReturnToMainMenu = onReturnToMainMenu;
    }

    public void showContinueMenu() {
        Button returnToMainMenuButton = new Button("Return to Main Menu");
        returnToMainMenuButton.setOnAction(e -> {
            sound.playButtonSound();
            onReturnToMainMenu.run();
        });
        returnToMainMenuButton.getStyleClass().add("button");

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            sound.playButtonSound();
            onExit.run();
        });
        exitButton.getStyleClass().add("button");

        // Layout Setup
        VBox layout = new VBox(20, returnToMainMenuButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");
        layout.getStylesheets().add(getClass().getResource(CONTINUE_MENU_STYLE).toExternalForm());
        this.setPrefSize(300, 250);
        this.setLayoutX(500);
        this.setLayoutY(200);
        this.getChildren().add(layout);
        this.toFront();
        this.setVisible(false);
    }
}
