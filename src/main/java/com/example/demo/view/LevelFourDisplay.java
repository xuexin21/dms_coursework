package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class LevelFourDisplay extends StackPane {
    private static final int xPosition = Main.SCREEN_WIDTH - 220;
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;
    private final Label LevelFourText;

    public LevelFourDisplay() {
        this.LevelFourText = new Label("LEVEL FOUR");
        this.LevelFourText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(LevelFourText);
    }

    public void hideLevelFourText() {
        this.LevelFourText.setVisible(false);
    }
}
