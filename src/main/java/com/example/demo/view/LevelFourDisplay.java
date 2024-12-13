package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * The LevelFourDisplay class is responsible for displaying the "LEVEL FOUR" label in the game.
 * This label is shown when the game reaches Level Four.
 */
public class LevelFourDisplay extends StackPane {

    /**
     * The X position of the "LEVEL FOUR" display on the screen.
     */
    private static final int xPosition = Main.SCREEN_WIDTH - 220;

    /**
     * The Y position of the "LEVEL FOUR" display on the screen.
     */
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;

    /**
     * The label that displays the "LEVEL FOUR" text.
     */
    private final Label LevelFourText;

    /**
     * Constructs a new LevelFourDisplay object and initializes the label to display "LEVEL FOUR".
     * The label is positioned on the screen at the specified coordinates.
     */
    public LevelFourDisplay() {
        this.LevelFourText = new Label("LEVEL FOUR");
        this.LevelFourText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(LevelFourText);
    }

    /**
     * Hides the "LEVEL FOUR" text from view.
     */
    public void hideLevelFourText() {
        this.LevelFourText.setVisible(false);
    }
}
