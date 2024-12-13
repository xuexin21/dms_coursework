package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * The LevelThreeDisplay class is responsible for displaying the "LEVEL THREE" label in the game.
 * This label is shown when the game reaches Level Three.
 */
public class LevelThreeDisplay extends StackPane {

    /**
     * The X position of the "LEVEL THREE" display on the screen.
     */
    private static final int xPosition = Main.SCREEN_WIDTH - 220;

    /**
     * The Y position of the "LEVEL THREE" display on the screen.
     */
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;

    /**
     * The label that displays the "LEVEL THREE" text.
     */
    private final Label LevelThreeText;

    /**
     * Constructs a new LevelThreeDisplay object and initializes the label to display "LEVEL THREE".
     * The label is positioned on the screen at the specified coordinates.
     */
    public LevelThreeDisplay() {
        this.LevelThreeText = new Label("LEVEL THREE");
        this.LevelThreeText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(LevelThreeText);
    }

    /**
     * Hides the "LEVEL THREE" text from view.
     */
    public void hideLevelThreeText() {
        this.LevelThreeText.setVisible(false);
    }
}
