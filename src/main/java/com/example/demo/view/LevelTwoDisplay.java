package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * The LevelTwoDisplay class is responsible for displaying the "LEVEL TWO" label in the game.
 * This label is shown when the game reaches Level Two.
 */
public class LevelTwoDisplay extends StackPane {

    /**
     * The X position of the "LEVEL TWO" display on the screen.
     */
    private static final int xPosition = Main.SCREEN_WIDTH - 220;

    /**
     * The Y position of the "LEVEL TWO" display on the screen.
     */
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;

    /**
     * The label that displays the "LEVEL TWO" text.
     */
    private final Label LevelTwoText;

    /**
     * Constructs a new LevelTwoDisplay object and initializes the label to display "LEVEL TWO".
     * The label is positioned on the screen at the specified coordinates.
     */
    public LevelTwoDisplay() {
        this.LevelTwoText = new Label("LEVEL TWO");
        this.LevelTwoText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(LevelTwoText);
    }

    /**
     * Hides the "LEVEL TWO" text from view.
     */
    public void hideLevelTwoText() {
        this.LevelTwoText.setVisible(false);
    }
}
