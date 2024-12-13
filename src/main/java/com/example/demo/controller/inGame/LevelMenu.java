package com.example.demo.controller.inGame;

import com.example.demo.level.LevelParent;
import com.example.demo.controller.Main;
import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static com.example.demo.level.LevelParent.nextLevelName;

/**
 * The LevelMenu class represents the in-game menu that appears during or after a level.
 * It provides options for the player to either proceed to the next level or return to the main menu.
 * The menu is interactive and plays sound effects for user interactions.
 */
public class LevelMenu extends StackPane {

    /**
     * The path to the stylesheet for the level menu button styling.
     */
    private static final String LEVEL_MENU_STYLE = "/com/example/demo/styles/small_menu.css";

    /**
     * The Music object to control background music.
     */
    private final Music music;

    /**
     * The Sound object to control sound effects.
     */
    private final Sound sound;

    /**
     * A callback to return to the main menu when the player chooses the "Return to Main Menu" option.
     */
    private final Runnable onReturnToMainMenu;

    /**
     * The parent level that the menu belongs to. This is used to handle transitions to the next level.
     */
    private final LevelParent level;

    /**
     * Constructs a LevelMenu object with the specified level, main menu return callback,
     * music, and sound objects.
     *
     * @param levelParent the parent level associated with the menu
     * @param onReturnToMainMenu a callback to run when returning to the main menu
     * @param music the Music object for controlling background music
     * @param sound the Sound object for controlling sound effects
     */
    public LevelMenu(LevelParent levelParent, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = music;
        this.sound = sound;
        this.level = levelParent;
        this.onReturnToMainMenu = onReturnToMainMenu;
    }

    /**
     * Sets up and shows the level menu, including buttons for the next level and returning to the main menu.
     * The menu layout is styled and positioned based on the screen dimensions.
     */
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

        VBox layout = new VBox(20, nextLevelButton, returnToMainMenuButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");
        layout.getStylesheets().add(getClass().getResource(LEVEL_MENU_STYLE).toExternalForm());

        double WIDTH = Main.SCREEN_WIDTH / 2 - 190;
        double HEIGHT = Main.SCREEN_HEIGHT / 2 - 165;
        this.setPrefSize(400, 250);
        this.setLayoutX(WIDTH);
        this.setLayoutY(HEIGHT);
        this.getChildren().add(layout);
        this.toFront();
        this.setVisible(false);
    }

    /**
     * Transition to the next level by playing the next level sound and calling the
     * method in the parent level to initiate the transition.
     *
     * @param levelName the name of the next level to transition to
     */
    public void nextLevel(String levelName) {
        sound.playNextLevelSound();
        level.goToNextLevel(levelName);
    }
}
