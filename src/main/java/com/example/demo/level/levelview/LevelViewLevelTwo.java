package com.example.demo.level.levelview;

import com.example.demo.model.Boss;
import com.example.demo.view.BossHealthDisplay;
import com.example.demo.view.LevelTwoDisplay;
import com.example.demo.view.ShieldImage;
import javafx.scene.Group;

/**
 * The LevelViewLevelTwo class extends the LevelView class and manages the visual elements specific to level two.
 * It handles the display of the shield, boss health, and level-specific graphics for the second level.
 */
public class LevelViewLevelTwo extends LevelView {

	/**
	 * The root group that contains all the visual elements for level two.
	 */
	private final Group root;

	/**
	 * The shield image for the boss in level two.
	 */
	private final ShieldImage shieldImage;

	/**
	 * The health display for the boss in level two.
	 */
	private final BossHealthDisplay bossHealthDisplay;

	/**
	 * The display of the level two specific elements, such as background or level text.
	 */
	private final LevelTwoDisplay levelTwoDisplay;

	/**
	 * Constructs a LevelViewLevelTwo object, initializing the necessary visual elements for level two.
	 *
	 * @param root the root group that holds the visual elements for the level
	 * @param heartsToDisplay the initial number of hearts to display
	 * @param bossHealth the health value of the boss to be displayed
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay, int bossHealth) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage();
		this.bossHealthDisplay = new BossHealthDisplay(bossHealth);
		this.levelTwoDisplay = new LevelTwoDisplay();
	}

	/**
	 * Displays the shield image for the boss and brings it to the front of the view.
	 */
	public void showShield() {
		shieldImage.showShield();
		shieldImage.toFront();
	}

	/**
	 * Hides the shield image for the boss.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}

	/**
	 * Displays the shield image for the boss on the screen.
	 */
	public void displayShield() {
		root.getChildren().add(shieldImage);
	}

	/**
	 * Updates the position of the shield image relative to the boss's position.
	 *
	 * @param boss the boss object whose position is used to update the shield's position
	 */
	public void updateShieldPosition(Boss boss) {
		double bossPositionX = boss.getLayoutX() + boss.getTranslateX() - 10;
		double bossPositionY = boss.getLayoutY() + boss.getTranslateY() + 20;
		shieldImage.setLayout(bossPositionX, bossPositionY);
	}

	/**
	 * Displays the boss health on the screen.
	 */
	public void showBossHealth() {
		root.getChildren().add(bossHealthDisplay);
	}

	/**
	 * Hides the boss health display from the screen.
	 */
	public void hideBossHealth() {
		bossHealthDisplay.hideBossHealth();
	}

	/**
	 * Updates the position of the boss health display relative to the boss's position.
	 *
	 * @param boss the boss object whose position is used to update the health display's position
	 */
	public void updateHealthDisplayPosition(Boss boss) {
		double positionX = boss.getLayoutX() + boss.getTranslateX() + 3;
		double positionY = boss.getLayoutY() + boss.getTranslateY() + 85;
		bossHealthDisplay.setLayout(positionX, positionY);
	}

	/**
	 * Updates the displayed health of the boss.
	 *
	 * @param bossHealth the current health value of the boss
	 */
	public void updateBossHealth(int bossHealth) {
		bossHealthDisplay.updateBossHealth(bossHealth);
	}

	/**
	 * Displays the level two specific graphics, including background and level text.
	 */
	public void showLevelTwo() {
		root.getChildren().add(levelTwoDisplay);
	}

	/**
	 * Hides the level two specific graphics from the screen.
	 */
	public void hideLevelTwo() {
		levelTwoDisplay.hideLevelTwoText();
	}
}
