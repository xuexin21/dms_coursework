package com.example.demo.controller.inGame;

import com.example.demo.controller.Main;
import com.example.demo.level.LevelParent;
import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import static com.example.demo.level.LevelParent.currentLevelName;

public class LoseMenu extends StackPane {

    private static final String CONTINUE_MENU_STYLE = "/com/example/demo/styles/small_menu.css";
    private final Music music;
    private final Sound sound;
    private final Runnable onExit;
    private final Runnable onReturnToMainMenu;
    private final LevelParent level;

    public LoseMenu(LevelParent levelParent, Runnable onExit, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = music;
        this.sound = sound;
        this.onExit = onExit;
        this.onReturnToMainMenu = onReturnToMainMenu;
        this.level = levelParent;
    }

    public void showLoseMenu() {

        Button replayButton = new Button("Play again");
        replayButton.setOnAction(e -> {
            sound.playButtonSound();
            replayThisLevel(currentLevelName);
            sound.playGameStartSound();
        });
        replayButton.getStyleClass().add("button");

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            sound.playButtonSound();
            onExit.run();
        });
        exitButton.getStyleClass().add("button");

        Button returnToMainMenuButton = new Button("Return to Main Menu");
        returnToMainMenuButton.setOnAction(e -> {
            sound.playButtonSound();
            onReturnToMainMenu.run();
        });
        returnToMainMenuButton.getStyleClass().add("button");

        // Layout Setup
        HBox layout = new HBox(30, replayButton, exitButton, returnToMainMenuButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");
        layout.getStylesheets().add(getClass().getResource(CONTINUE_MENU_STYLE).toExternalForm());
        double WIDTH = Main.SCREEN_WIDTH/4 - 30;
        double HEIGHT = Main.SCREEN_HEIGHT/2 + 130;
        this.setPrefSize(700, 80);
        this.setLayoutX(WIDTH);
        this.setLayoutY(HEIGHT);
        this.getChildren().add(layout);
        this.toFront();
        this.setVisible(false);
    }

    public void replayThisLevel(String levelName) {
        level.goToNextLevel(levelName);
    }
}
