package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class KillCountDisplay extends StackPane {
    private static final int xPosition = Main.SCREEN_WIDTH - 200;
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;
    private final int initialKillCount;
    private final Label killCountText;

    public KillCountDisplay(int initialKillCount) {
        this.initialKillCount = initialKillCount;
        this.killCountText = new Label("Kills: 0/" + initialKillCount);
        this.killCountText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(killCountText);
    }

    public void updateKillCount(int killCount) {
        this.killCountText.setText("Kills: " + killCount + "/" + initialKillCount);
    }

    public void hideKillCount() {
        this.killCountText.setVisible(false);
    }
}
