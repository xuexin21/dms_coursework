package com.example.demo.controller.inGame;

import com.example.demo.audio.Sound;
import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The PauseButton class represents a button that triggers a pause action in the game.
 * It displays a pause button image and plays a sound when clicked.
 */
public class PauseButton extends ImageView {

    /**
     * The path to the pause button image file.
     */
    private static final String PAUSE_BUTTON_IMAGE = "/com/example/demo/images/pausebutton.png";

    /**
     * The size of the pause button.
     */
    private static final int PAUSE_BUTTON_SIZE = 50;

    /**
     * The x-coordinate for positioning the pause button on the screen.
     */
    private static final double xPosition = Main.SCREEN_WIDTH - 100;

    /**
     * The y-coordinate for positioning the pause button on the screen.
     */
    private static final int yPosition = Main.SCREEN_HEIGHT - 740;

    /**
     * The Sound object used to play sound effects.
     */
    private Sound sound;

    /**
     * Constructs a PauseButton object at a fixed position with an action to pause the game.
     * The button triggers the provided onPause action and plays a sound when clicked.
     *
     * @param onPause the Runnable to be executed when the button is clicked (typically to pause the game)
     * @param sound the Sound object used to play button click sounds
     */
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

    /**
     * Hides the pause button by setting its visibility to false.
     */
    public void hidePauseButton() {
        this.setVisible(false);
    }
}
