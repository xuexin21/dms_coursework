package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * The KillCountDisplay class is responsible for displaying the player's kill count in the game.
 * It manages the visual representation of the number of kills, which is updated as the player progresses.
 */
public class KillCountDisplay extends StackPane {

    /**
     * The X position of the kill count display on the screen.
     */
    private static final int xPosition = Main.SCREEN_WIDTH - 220;

    /**
     * The Y position of the kill count display on the screen.
     */
    private static final int yPosition = Main.SCREEN_HEIGHT - 715;

    /**
     * The initial kill count value.
     */
    private final int initialKillCount;

    /**
     * The label that displays the kill count text.
     */
    private final Label killCountText;

    /**
     * Constructs a new KillCountDisplay object, initializes the kill count label, and sets its position on the screen.
     *
     * @param initialKillCount The initial number of kills to display.
     */
    public KillCountDisplay(int initialKillCount) {
        this.initialKillCount = initialKillCount;
        this.killCountText = new Label("Kills: 0/" + initialKillCount);
        this.killCountText.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.getChildren().add(killCountText);
    }

    /**
     * Updates the kill count text displayed to the user.
     *
     * @param killCount The current number of kills to display.
     */
    public void updateKillCount(int killCount) {
        this.killCountText.setText("Kills: " + killCount + "/" + initialKillCount);
    }

    /**
     * Hides the kill count display from view.
     */
    public void hideKillCount() {
        this.killCountText.setVisible(false);
    }
}
