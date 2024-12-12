package com.example.demo.controller.inGame;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import com.example.demo.controller.Main;
import com.example.demo.level.LevelParent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static com.example.demo.level.LevelParent.nextLevelName;

public class LevelMenu extends StackPane {

    private static final String CONTINUE_MENU_STYLE = "/com/example/demo/styles/small_menu.css";
    private final Music music;
    private final Sound sound;
    private final Runnable onReturnToMainMenu;
    private final LevelParent level;

    public LevelMenu(LevelParent levelParent, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = music;
        this.sound = sound;
        this.level = levelParent;
        this.onReturnToMainMenu = onReturnToMainMenu;
    }

    public void showLevelMenu() {
        Button nextLevelButton = new Button("Next Level");
        nextLevelButton.setOnAction(e -> {
            sound.playButtonSound();
            nextLevel(nextLevelName);
        });
        nextLevelButton.getStyleClass().add("button");

        Button returnToMainMenuButton = new Button("Return to Main Menu");
        returnToMainMenuButton.setOnAction(e -> {
            sound.playButtonSound();
            onReturnToMainMenu.run();
        });
        returnToMainMenuButton.getStyleClass().add("button");

        // Layout Setup
        VBox layout = new VBox(20, nextLevelButton,returnToMainMenuButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");
        layout.getStylesheets().add(getClass().getResource(CONTINUE_MENU_STYLE).toExternalForm());
        double WIDTH = Main.SCREEN_WIDTH/2 - 190;
        double HEIGHT = Main.SCREEN_HEIGHT/2 - 165;
        this.setPrefSize(400, 250);
        this.setLayoutX(WIDTH);
        this.setLayoutY(HEIGHT);
        this.getChildren().add(layout);
        this.toFront();
        this.setVisible(false);
    }

    public void nextLevel(String levelName) {
        sound.playNextLevelSound();
        level.goToNextLevel(levelName);
    }
}
