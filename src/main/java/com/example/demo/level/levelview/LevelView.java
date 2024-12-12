package com.example.demo.level.levelview;

import com.example.demo.controller.Main;
import com.example.demo.view.WinImage;
import com.example.demo.view.GameOverImage;
import com.example.demo.view.HeartDisplay;
import javafx.scene.Group;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = (double) Main.SCREEN_WIDTH / 2 - 645;
	private static final double HEART_DISPLAY_Y_POSITION = (double) Main.SCREEN_HEIGHT / 2 - 350;
	private static final double WIN_IMAGE_X_POSITION = (double) Main.SCREEN_WIDTH/2 - 240;
	private static final double WIN_IMAGE_Y_POSITION = (double) Main.SCREEN_HEIGHT / 2 - 200;
	private static final double LOSS_SCREEN_X_POSITION = (double) Main.SCREEN_WIDTH / 2 - 330;
	private static final double LOSS_SCREEN_Y_POSITION = (double) Main.SCREEN_HEIGHT / 2 - 285;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
		gameOverImage.showGameOverImage();
	}
	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	public void addHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < heartsRemaining - currentNumberOfHearts; i++) {
			heartDisplay.addHeart();
		}
	}
}
