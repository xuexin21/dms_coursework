package com.example.demo.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

public class BossHealthDisplay extends StackPane {
    private int bossInitialHealth;
    private final ProgressBar healthBar;
    private final Label healthText;
    private static final int BAR_WIDTH = 370;
    private static final int BAR_HEIGHT = 20;

    public BossHealthDisplay(int bossHealth) {
        this.bossInitialHealth = bossHealth;
        this.healthBar = new ProgressBar(1.0);
        this.healthText = new Label(bossHealth + "/" + bossHealth);
        this.healthText.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
        this.healthBar.setPrefWidth(BAR_WIDTH);
        this.healthBar.setPrefHeight(BAR_HEIGHT);
        this.healthBar.setStyle("-fx-accent: red; -fx-control-inner-background: transparent;");
        this.getChildren().addAll(healthBar, healthText);
    }

    public void updateBossHealth(int bossHealth) {
        double progress = (double) bossHealth / bossInitialHealth;
        this.healthBar.setProgress(progress);
        this.healthText.setText(bossHealth + "/" + bossInitialHealth);
    }

    public void setLayout(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void resetHealth(int bossHealth) {
        this.bossInitialHealth = bossHealth;
        this.healthBar.setProgress(1.0);
        this.healthText.setText(bossHealth + "/" + bossHealth);
        this.healthBar.setStyle("-fx-accent: darkred; -fx-control-inner-background: gray;");
    }

    public void hide() {
        this.setVisible(false);
    }

    public int getCurrentHealth() {
        return Integer.parseInt(this.healthText.getText().split("/")[0]);
    }
}