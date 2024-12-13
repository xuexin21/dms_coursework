package com.example.demo.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

/**
 * The BossHealthDisplay class is responsible for displaying the health bar of the boss in the game.
 * It includes a progress bar that visually represents the boss's current health and a label showing the health value.
 * The health bar's progress is updated as the boss's health changes.
 */
public class BossHealthDisplay extends StackPane {

    /**
     * The initial health of the boss when the game starts.
     */
    private final int bossInitialHealth;

    /**
     * The progress bar representing the boss's health.
     */
    private final ProgressBar healthBar;

    /**
     * The label displaying the boss's current and initial health.
     */
    private final Label healthText;

    /**
     * The width of the health bar.
     */
    private static final int BAR_WIDTH = 370;

    /**
     * The height of the health bar.
     */
    private static final int BAR_HEIGHT = 20;

    /**
     * Constructs a new BossHealthDisplay with the specified initial health of the boss.
     * Initializes the progress bar and label, and sets up the initial display for the boss's health.
     *
     * @param bossHealth The initial health of the boss.
     */
    public BossHealthDisplay(int bossHealth) {
        this.bossInitialHealth = bossHealth;
        this.healthBar = new ProgressBar(1.0); // Starts with full health.
        this.healthText = new Label(bossHealth + "/" + bossHealth);
        this.healthText.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
        this.healthBar.setPrefWidth(BAR_WIDTH);
        this.healthBar.setPrefHeight(BAR_HEIGHT);
        this.healthBar.setStyle("-fx-accent: red; -fx-control-inner-background: transparent;");
        this.getChildren().addAll(healthBar, healthText);
    }

    /**
     * Updates the health bar and label to reflect the boss's current health.
     * This method is called whenever the boss's health changes.
     *
     * @param bossHealth The current health of the boss.
     */
    public void updateBossHealth(int bossHealth) {
        double progress = (double) bossHealth / bossInitialHealth;
        this.healthBar.setProgress(progress);
        this.healthText.setText(bossHealth + "/" + bossInitialHealth);
    }

    /**
     * Sets the position of the BossHealthDisplay on the screen.
     *
     * @param x The X position of the display.
     * @param y The Y position of the display.
     */
    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    /**
     * Hides the health bar from the screen.
     * This can be called when the boss is defeated or when the health bar is no longer needed.
     */
    public void hideBossHealth() {
        this.setVisible(false);
    }
}
