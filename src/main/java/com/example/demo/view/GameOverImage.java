package com.example.demo.view;

import com.example.demo.controller.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
	private static final double WIDTH = Main.SCREEN_WIDTH/2 - 50;
	private static final double HEIGHT = Main.SCREEN_HEIGHT/2 + 25;

	public GameOverImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.toFront();
	}

	public void showGameOverImage() {
		this.setVisible(true);
	}
}
