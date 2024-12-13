package com.example.demo.level.levelview;

import com.example.demo.view.KillCountDisplay;
import com.example.demo.view.LevelThreeDisplay;
import javafx.scene.Group;

/**
 * The LevelViewLevelThree class extends the LevelView class and is responsible for managing the visual elements
 * specific to level three in the game. It handles the display of the kill count, level-specific text, and other
 * graphics associated with level three.
 */
public class LevelViewLevelThree extends LevelView {

	/**
	 * The root group that contains all the visual elements for level three.
	 */
	private final Group root;

	/**
	 * The kill count display for the level, which tracks how many kills are required to advance.
	 */
	private final KillCountDisplay killCountDisplay;

	/**
	 * The display of the level three specific elements, such as background or level text.
	 */
	private final LevelThreeDisplay levelThreeDisplay;

	/**
	 * Constructs a LevelViewLevelThree object, initializing the necessary visual elements for level three.
	 *
	 * @param root the root group that holds the visual elements for the level
	 * @param heartsToDisplay the initial number of hearts to display
	 * @param killsToAdvance the number of kills required to advance to the next level
	 */
	public LevelViewLevelThree(Group root, int heartsToDisplay, int killsToAdvance) {
		super(root, heartsToDisplay);
		this.root = root;
		this.killCountDisplay = new KillCountDisplay(killsToAdvance);
		this.levelThreeDisplay = new LevelThreeDisplay();
	}

	/**
	 * Displays the kill count on the screen for level three.
	 */
	public void showKillCount() {
		root.getChildren().add(killCountDisplay);
	}

	/**
	 * Updates the kill count display with the current number of kills.
	 *
	 * @param killCount the current number of kills
	 */
	public void updateKillCount(int killCount) {
		killCountDisplay.updateKillCount(killCount);
	}

	/**
	 * Hides the kill count display from the screen.
	 */
	public void hideKillCount() {
		killCountDisplay.hideKillCount();
	}

	/**
	 * Displays the level three specific graphics, including background and text.
	 */
	public void showLevelThree() {
		root.getChildren().add(levelThreeDisplay);
	}

	/**
	 * Hides the level three specific graphics from the screen.
	 */
	public void hideLevelThree() {
		levelThreeDisplay.hideLevelThreeText();
	}
}
