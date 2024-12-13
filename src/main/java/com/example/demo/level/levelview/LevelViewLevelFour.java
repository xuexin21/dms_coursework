package com.example.demo.level.levelview;

import com.example.demo.model.Boss;
import com.example.demo.model.SecondBoss;
import com.example.demo.view.BossHealthDisplay;
import com.example.demo.view.LevelFourDisplay;
import com.example.demo.view.ShieldImage;
import javafx.scene.Group;

/**
 * The LevelViewLevelFour class extends the LevelView class and is responsible for managing the visual elements
 * specific to level four. It handles the display of boss shields, boss health, and level-specific graphics
 * (such as the LevelFour display).
 */
public class LevelViewLevelFour extends LevelView {

	/**
	 * The root group that holds all visual elements for level four.
	 */
	private final Group root;

	/**
	 * The shield image for the first boss.
	 */
	private final ShieldImage firstBossShieldImage;

	/**
	 * The shield image for the second boss.
	 */
	private final ShieldImage secondBossShieldImage;

	/**
	 * The level four display elements.
	 */
	private final LevelFourDisplay levelFourDisplay;

	/**
	 * The health display for the first boss.
	 */
	private final BossHealthDisplay firstBossHealthDisplay;

	/**
	 * The health display for the second boss.
	 */
	private final BossHealthDisplay secondBossHealthDisplay;

	/**
	 * Constructs a LevelViewLevelFour object, initializing the visual elements specific to level four.
	 *
	 * @param root the root group that holds the visual elements for the level
	 * @param heartsToDisplay the initial number of hearts to display
	 * @param bossHealth the initial health of both bosses
	 */
	public LevelViewLevelFour(Group root, int heartsToDisplay, int bossHealth) {
		super(root, heartsToDisplay);
		this.root = root;
		this.firstBossShieldImage = new ShieldImage();
		this.secondBossShieldImage = new ShieldImage();
		this.levelFourDisplay = new LevelFourDisplay();
		this.firstBossHealthDisplay = new BossHealthDisplay(bossHealth);
		this.secondBossHealthDisplay = new BossHealthDisplay(bossHealth);
	}

	/**
	 * Shows the shield for the first boss.
	 */
	public void firstBossShowShield() {
		firstBossShieldImage.showShield();
		firstBossShieldImage.toFront();
	}

	/**
	 * Hides the shield for the first boss.
	 */
	public void firstBossHideShield() {
		firstBossShieldImage.hideShield();
	}

	/**
	 * Displays the shield for the first boss.
	 */
	public void firstBossDisplayShield() {
		root.getChildren().add(firstBossShieldImage);
	}

	/**
	 * Updates the position of the first boss's shield based on the boss's current position.
	 *
	 * @param boss the first boss whose position is used to update the shield's position
	 */
	public void firstBossUpdateShieldPosition(Boss boss) {
		double bossPositionX = boss.getLayoutX() + boss.getTranslateX() - 10;
		double bossPositionY = boss.getLayoutY() + boss.getTranslateY() + 20;
		firstBossShieldImage.setLayout(bossPositionX, bossPositionY);
	}

	/**
	 * Shows the shield for the second boss.
	 */
	public void secondBossShowShield() {
		secondBossShieldImage.showShield();
		secondBossShieldImage.toFront();
	}

	/**
	 * Hides the shield for the second boss.
	 */
	public void secondBossHideShield() {
		secondBossShieldImage.hideShield();
	}

	/**
	 * Displays the shield for the second boss.
	 */
	public void secondBossDisplayShield() {
		root.getChildren().add(secondBossShieldImage);
	}

	/**
	 * Updates the position of the second boss's shield based on the boss's current position.
	 *
	 * @param boss2 the second boss whose position is used to update the shield's position
	 */
	public void secondBossUpdateShieldPosition(SecondBoss boss2) {
		double bossPositionX = boss2.getLayoutX() + boss2.getTranslateX() - 10;
		double bossPositionY = boss2.getLayoutY() + boss2.getTranslateY() + 20;
		secondBossShieldImage.setLayout(bossPositionX, bossPositionY);
	}

	/**
	 * Displays the health of the first boss.
	 */
	public void firstBossShowBossHealth() {
		root.getChildren().add(firstBossHealthDisplay);
	}

	/**
	 * Hides the health of the first boss.
	 */
	public void firstBossHideBossHealth() {
		firstBossHealthDisplay.hideBossHealth();
	}

	/**
	 * Updates the position of the first boss's health display based on the boss's current position.
	 *
	 * @param boss the first boss whose position is used to update the health display's position
	 */
	public void firstBossUpdateHealthDisplayPosition(Boss boss) {
		double positionX = boss.getLayoutX() + boss.getTranslateX() + 3;
		double positionY = boss.getLayoutY() + boss.getTranslateY() + 85;
		firstBossHealthDisplay.setLayout(positionX, positionY);
	}

	/**
	 * Updates the health display for the first boss.
	 *
	 * @param bossHealth the updated health of the first boss
	 */
	public void firstBossUpdateBossHealth(int bossHealth) {
		firstBossHealthDisplay.updateBossHealth(bossHealth);
	}

	/**
	 * Displays the health of the second boss.
	 */
	public void secondBossShowBossHealth() {
		root.getChildren().add(secondBossHealthDisplay);
	}

	/**
	 * Hides the health of the second boss.
	 */
	public void secondBossHideBossHealth() {
		secondBossHealthDisplay.hideBossHealth();
	}

	/**
	 * Updates the position of the second boss's health display based on the boss's current position.
	 *
	 * @param boss2 the second boss whose position is used to update the health display's position
	 */
	public void secondBossUpdateHealthDisplayPosition(SecondBoss boss2) {
		double positionX = boss2.getLayoutX() + boss2.getTranslateX() + 3; // Center it below the boss
		double positionY = boss2.getLayoutY() + boss2.getTranslateY() + 85; // Adjust the Y position
		secondBossHealthDisplay.setLayout(positionX, positionY);
	}

	/**
	 * Updates the health display for the second boss.
	 *
	 * @param bossHealth the updated health of the second boss
	 */
	public void secondBossUpdateBossHealth(int bossHealth) {
		secondBossHealthDisplay.updateBossHealth(bossHealth);
	}

	/**
	 * Displays the level four text and background.
	 */
	public void showLevelFour() {
		root.getChildren().add(levelFourDisplay);
	}

	/**
	 * Hides the level four text and background.
	 */
	public void hideLevelFour() {
		levelFourDisplay.hideLevelFourText();
	}
}
