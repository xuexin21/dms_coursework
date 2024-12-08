package com.example.demo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PowerUp extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/powerup.png";
    public static final int IMAGE_SIZE = 60;
    private Timeline timer;

    public PowerUp() {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(IMAGE_SIZE);
        this.setFitWidth(IMAGE_SIZE);
        initializeTimer();
    }

    private void initializeTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(3), e -> hidePowerUp()));
        timer.setCycleCount(1); // Only run once
    }

    public void showPowerUp() {
        this.setVisible(true);
        timer.playFromStart(); // Start the timer when the power-up is shown
    }

    public void hidePowerUp() {
        this.setVisible(false);
        timer.stop(); // Stop the timer when hiding
    }

    public void setLayout(double xBound, double yBound) {
        this.setLayoutX(xBound);
        this.setLayoutY(yBound);
    }
}
