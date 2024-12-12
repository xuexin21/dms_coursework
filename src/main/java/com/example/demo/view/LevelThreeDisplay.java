package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class LevelThreeDisplay extends StackPane {
    private static final int xPosition = Main.SCREEN_WIDTH - 220;
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;
    private final Label LevelThreeText;

    public LevelThreeDisplay() {
        this.LevelThreeText = new Label("LEVEL THREE");
        this.LevelThreeText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(LevelThreeText);
    }

    public void hideLevelThreeText() {
        this.LevelThreeText.setVisible(false);
    }
}
