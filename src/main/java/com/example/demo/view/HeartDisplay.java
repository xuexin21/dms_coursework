package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The HeartDisplay class is responsible for displaying a set of heart images that represent the player's health in the game.
 * It manages the display of hearts and provides methods to add or remove hearts as the player's health changes.
 */
public class HeartDisplay {

	/**
	 * The path to the heart image file.
	 */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	/**
	 * The height of each heart image.
	 */
	private static final int HEART_HEIGHT = 50;

	/**
	 * The index of the first item in the heart container, used for removing hearts.
	 */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/**
	 * The container (HBox) that holds the heart images.
	 */
	private HBox container;

	/**
	 * The X position of the container on the screen.
	 */
	private double containerXPosition;

	/**
	 * The Y position of the container on the screen.
	 */
	private double containerYPosition;

	/**
	 * The number of hearts to initially display.
	 */
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a new HeartDisplay object and initializes the heart container and the hearts to be displayed.
	 *
	 * @param xPosition The X position of the heart container on the screen.
	 * @param yPosition The Y position of the heart container on the screen.
	 * @param heartsToDisplay The number of hearts to initially display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) for holding the heart images.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the heart images to be displayed in the container.
	 * It adds the specified number of heart images to the container.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Adds a heart image to the container, increasing the number of hearts displayed.
	 */
	public void addHeart() {
		ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
		heart.setFitHeight(HEART_HEIGHT);
		heart.setPreserveRatio(true);
		container.getChildren().add(heart);
	}

	/**
	 * Removes a heart image from the container, decreasing the number of hearts displayed.
	 * If there are no hearts left, it does nothing.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Gets the container (HBox) holding the heart images.
	 *
	 * @return The HBox container holding the hearts.
	 */
	public HBox getContainer() {
		return container;
	}
}
