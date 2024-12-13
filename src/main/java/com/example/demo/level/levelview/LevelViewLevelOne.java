package com.example.demo.level.levelview;

import com.example.demo.view.KillCountDisplay;
import com.example.demo.view.LevelOneDisplay;
import javafx.scene.Group;

/**
 * The LevelViewLevelOne class extends the LevelView class and is responsible for managing the visual elements
 * specific to level one. It handles the display of the kill count, level-specific graphics, and other level-related visuals.
 */
public class LevelViewLevelOne extends LevelView {

	/**
	 * The root group that holds all visual elements for level one.
	 */
	private final Group root;

	/**
	 * The kill count display for level one.
	 */
	private final KillCountDisplay killCountDisplay;

	/**
	 * The level one display elements (e.g., level-specific text and background).
	 */
	private final LevelOneDisplay levelOneDisplay;

	/**
	 * Constructs a LevelViewLevelOne object, initializing the visual elements specific to level one.
	 *
	 * @param root the root group that holds the visual elements for the level
	 * @param heartsToDisplay the initial number of hearts to display
	 * @param killsToAdvance the number of kills required to advance to the next level
	 */
	public LevelViewLevelOne(Group root, int heartsToDisplay, int killsToAdvance) {
		super(root, heartsToDisplay);
		this.root = root;
		this.killCountDisplay = new KillCountDisplay(killsToAdvance);
		this.levelOneDisplay = new LevelOneDisplay();
	}

	/**
	 * Displays the kill count on the screen.
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
	 * Displays the level one specific graphics (text and background).
	 */
	public void showLevelOne() {
		root.getChildren().add(levelOneDisplay);
	}

	/**
	 * Hides the level one specific graphics (text and background).
	 */
	public void hideLevelOne() {
		levelOneDisplay.hideLevelOneText();
	}
}
