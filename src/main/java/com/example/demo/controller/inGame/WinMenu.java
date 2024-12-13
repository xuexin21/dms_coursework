package com.example.demo.controller.inGame;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import com.example.demo.controller.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * The WinMenu class represents the win menu that appears when the player wins the game.
 * It provides options to return to the main menu or exit the game.
 */
public class WinMenu extends StackPane {

    /**
     * The path to the win menu's stylesheet.
     */
    private static final String LEVEL_MENU_STYLE = "/com/example/demo/styles/small_menu.css";

    /**
     * The Music object for controlling background music settings.
     */
    private final Music music;

    /**
     * The Sound object for controlling sound effects settings.
     */
    private final Sound sound;

    /**
     * The action to be performed when the game is exited.
     */
    private final Runnable onExit;

    /**
     * The action to be performed when returning to the main menu.
     */
    private final Runnable onReturnToMainMenu;

    /**
     * Constructs a WinMenu object with options to return to the main menu or exit the game.
     *
     * @param onExit the Runnable to be executed when exiting the game
     * @param onReturnToMainMenu the Runnable to be executed when returning to the main menu
     * @param music the Music object to control background music
     * @param sound the Sound object to control sound effects
     */
    public WinMenu(Runnable onExit, Runnable onReturnToMainMenu, Music music, Sound sound) {
        this.music = music;
        this.sound = sound;
        this.onExit = onExit;
        this.onReturnToMainMenu = onReturnToMainMenu;
    }

    /**
     * Displays the win menu with buttons to return to the main menu or exit the game.
     */
    public void showWinMenu() {

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

        HBox layout = new HBox(30, returnToMainMenuButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #34495e; -fx-padding: 50;");
        layout.getStylesheets().add(getClass().getResource(LEVEL_MENU_STYLE).toExternalForm());
        double WIDTH = Main.SCREEN_WIDTH / 4 + 80;
        double HEIGHT = Main.SCREEN_HEIGHT / 2 + 100;
        this.setPrefSize(500, 50);
        this.setLayoutX(WIDTH);
        this.setLayoutY(HEIGHT);
        this.getChildren().add(layout);
        this.toFront();
        this.setVisible(false);
    }
}
