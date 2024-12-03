package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
	private static final int HEIGHT = 300;
	private static final int WIDTH = 400;

	public GameOverImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
//		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	public void showGameOverImage() {
		this.setVisible(true);
	}
}
