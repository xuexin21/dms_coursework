package com.example.demo.controller.inGame;

import com.example.demo.audio.Sound;
import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PauseButton extends ImageView {
    private static final String PAUSE_BUTTON_IMAGE = "/com/example/demo/images/pausebutton.png";
    private static final int PAUSE_BUTTON_SIZE = 50;
    private static final double xPosition = Main.SCREEN_WIDTH - 100;
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;
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