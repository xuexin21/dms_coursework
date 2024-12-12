package com.example.demo.ui;

import com.example.demo.audio.Sound;
import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ContinueButton extends ImageView {
    private static final String CONTINUE_BUTTON_IMAGE = "/com/example/demo/images/continue.png";
    private static final double CONTINUE_BUTTON_WIDTH = Main.SCREEN_WIDTH/2 - 200;
    private static final double CONTINUE_BUTTON_HEIGHT = Main.SCREEN_HEIGHT/2 - 195;
    private static final int xPosition = 420;
    private static final int yPosition = 450;
    private Sound sound;

    public ContinueButton(Runnable onContinue, Sound sound) {
        this.sound = sound;
        this.setImage(new Image(getClass().getResource(CONTINUE_BUTTON_IMAGE).toExternalForm()));
        this.setFitHeight(CONTINUE_BUTTON_HEIGHT);
        this.setFitWidth(CONTINUE_BUTTON_WIDTH);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setVisible(false);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            sound.playButtonSound();
            onContinue.run();
        });
    }

    public void showContinueButton() {
        this.toFront();
        this.setVisible(true);
    }
}
