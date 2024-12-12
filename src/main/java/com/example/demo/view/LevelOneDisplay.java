package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class LevelOneDisplay extends StackPane {
    private static final int xPosition = Main.SCREEN_WIDTH - 220;
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;
    private final Label LevelOneText;

    public LevelOneDisplay() {
        this.LevelOneText = new Label("LEVEL ONE");
        this.LevelOneText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(LevelOneText);
    }

    public void hideLevelOneText() {
        this.LevelOneText.setVisible(false);
    }
}
