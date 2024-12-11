package com.example.demo.ui;

import com.example.demo.audio.Sound;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PauseButton extends ImageView {
    private static final String PAUSE_BUTTON_IMAGE = "/com/example/demo/images/pausebutton.png";
    private static final int PAUSE_BUTTON_SIZE = 50;
    private static final int xPosition = 1200;
    private static final int yPosition = 10;
    private Sound sound;

    public PauseButton(Runnable onPause, Sound sound) {
        this.sound = sound;
        this.setImage(new Image(getClass().getResource(PAUSE_BUTTON_IMAGE).toExternalForm()));
        this.setFitHeight(PAUSE_BUTTON_SIZE);
        this.setFitWidth(PAUSE_BUTTON_SIZE);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            sound.playButtonSound();
            onPause.run();
        });
    }

    public void hidePauseButton () {
        this.setVisible(false);
    }
}