package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * The LevelOneDisplay class is responsible for displaying the "LEVEL ONE" label in the game.
 * This label is shown when the game starts at Level One.
 */
public class LevelOneDisplay extends StackPane {

    /**
     * The X position of the "LEVEL ONE" display on the screen.
     */
    private static final int xPosition = Main.SCREEN_WIDTH - 220;

    /**
     * The Y position of the "LEVEL ONE" display on the screen.
     */
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;

    /**
     * The label that displays the "LEVEL ONE" text.
     */
    private final Label LevelOneText;

    /**
     * Constructs a new LevelOneDisplay object and initializes the label to display "LEVEL ONE".
     * The label is positioned on the screen at the specified coordinates.
     */
    public LevelOneDisplay() {
        this.LevelOneText = new Label("LEVEL ONE");
        this.LevelOneText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(LevelOneText);
    }

    /**
     * Hides the "LEVEL ONE" text from view.
     */
    public void hideLevelOneText() {
        this.LevelOneText.setVisible(false);
    }
}
