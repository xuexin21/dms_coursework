package com.example.demo.controller.inGame;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import com.example.demo.level.LevelParent;
import com.example.demo.controller.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import static com.example.demo.level.LevelParent.currentLevelName;

/**
 * The LoseMenu class represents the in-game menu that is displayed when the player loses the game.
 * It offers options to replay the current level, exit the game, or return to the main menu.
 * The menu includes interactive buttons with sound effects for each action.
 */
public class LoseMenu extends StackPane {

    /**
     * The path to the stylesheet for the lose menu button styling.
     */
    private static final String LOSE_MENU_STYLE = "/com/example/demo/styles/small_menu.css";

    /**
     * The Music object to control background music.
     */
    private final Music music;

    /**
     * The Sound object to control sound effects.
     */
    private final Sound sound;

    /**
     * A callback to exit the game when the player chooses the "Exit" option.
     */
    private final Runnable onExit;

    /**
     * A callback to return to the main menu when the player chooses the "Return to Main Menu" option.
     */
    private final Runnable onReturnToMainMenu;

    /**
     * The parent level that the menu belongs to. This is used to handle level transitions.
     */
    private final LevelParent level;

    /**
     * Constructs a LoseMenu object with the specified level, exit callback, main menu return callback,
     * music, and sound objects.
     *
     * @param levelParent the parent level associated with the menu
     * @param onExit a callback to run when the player chooses to exit the game
     * @param onReturnToMainMenu a callback to run when the player chooses to return to the main menu
     * @param music the Music object for controlling background music
     * @param sound the Sound object for controlling sound effects
     */
    public LoseMenu(LevelParent levelParent, Runnable onExit, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = music;
        this.sound = sound;
        this.onExit = onExit;
        this.onReturnToMainMenu = onReturnToMainMenu;
        this.level = levelParent;
    }

    /**
     * Sets up and shows the lose menu, including buttons for replaying the current level, exiting the game,
     * or returning to the main menu. The menu layout is styled and positioned based on the screen dimensions.
     */
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

        HBox layout = new HBox(30, replayButton, exitButton, returnToMainMenuButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");
        layout.getStylesheets().add(getClass().getResource(LOSE_MENU_STYLE).toExternalForm());

        double WIDTH = Main.SCREEN_WIDTH / 4 - 30;
        double HEIGHT = Main.SCREEN_HEIGHT / 2 + 130;
        this.setPrefSize(700, 80);
        this.setLayoutX(WIDTH);
        this.setLayoutY(HEIGHT);
        this.getChildren().add(layout);
        this.toFront();
        this.setVisible(false);
    }

    /**
     * Replays the current level by calling the method in the parent level to restart the level.
     *
     * @param levelName the name of the level to replay
     */
    public void replayThisLevel(String levelName) {
        level.goToNextLevel(levelName);
    }
}
