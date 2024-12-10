package com.example.demo.level.levelview;

import com.example.demo.model.Boss;
import com.example.demo.model.SecondBoss;
import com.example.demo.view.BossHealthDisplay;
import com.example.demo.view.ShieldImage;
import javafx.scene.Group;

public class LevelViewLevelFour extends LevelView {

	private final Group root;
	private final ShieldImage firstBossShieldImage;
	private final ShieldImage secondBossShieldImage;
	private final BossHealthDisplay firstBossHealthDisplay;
	private final BossHealthDisplay secondBossHealthDisplay;

	public LevelViewLevelFour(Group root, int heartsToDisplay, int bossHealth) {
		super(root, heartsToDisplay);
		this.root = root;
		this.firstBossShieldImage = new ShieldImage();
		this.secondBossShieldImage = new ShieldImage();
		this.firstBossHealthDisplay = new BossHealthDisplay(bossHealth);
		this.secondBossHealthDisplay = new BossHealthDisplay(bossHealth);
	}
	
	public void firstBossShowShield() {
		firstBossShieldImage.showShield();
		firstBossShieldImage.toFront();
	}

	public void firstBossHideShield() {
		firstBossShieldImage.hideShield();
	}

	public void firstBossDisplayShield() {
		root.getChildren().add(firstBossShieldImage);
	}

	public void firstBossUpdateShieldPosition(Boss boss){
		double bossPositionX =  boss.getLayoutX() + boss.getTranslateX() - 10;
		double bossPositionY = boss.getLayoutY() + boss.getTranslateY() + 20;
		firstBossShieldImage.setLayout(bossPositionX, bossPositionY);
	}

	public void secondBossShowShield() {
		secondBossShieldImage.showShield();
		secondBossShieldImage.toFront();
	}

	public void secondBossHideShield() {
		secondBossShieldImage.hideShield();
	}

	public void secondBossDisplayShield() {
		root.getChildren().add(secondBossShieldImage);
	}

	public void secondBossUpdateShieldPosition(SecondBoss boss2){
		double bossPositionX =  boss2.getLayoutX() + boss2.getTranslateX() - 10;
		double bossPositionY = boss2.getLayoutY() + boss2.getTranslateY() + 20;
		secondBossShieldImage.setLayout(bossPositionX, bossPositionY);
	}

	public void firstBossShowBossHealth() {
		root.getChildren().add(firstBossHealthDisplay);
	}

	public void firstBossHideBossHealth() {
		firstBossHealthDisplay.hideBossHealth();
	}

	public void firstBossUpdateHealthDisplayPosition(Boss boss) {
		double positionX = boss.getLayoutX() + boss.getTranslateX() + 3; // Center it below the boss
		double positionY = boss.getLayoutY() + boss.getTranslateY() + 85; // Adjust the Y position
		firstBossHealthDisplay.setLayout(positionX, positionY);
	}

	public void firstBossUpdateBossHealth(int bossHealth) {
		firstBossHealthDisplay.updateBossHealth(bossHealth);
	}

	public void SecondBossShowBossHealth() {
		root.getChildren().add(secondBossHealthDisplay);
	}

	public void secondBossHideBossHealth() {
		secondBossHealthDisplay.hideBossHealth();
	}

	public void secondBossUpdateHealthDisplayPosition(SecondBoss boss2) {
		double positionX = boss2.getLayoutX() + boss2.getTranslateX() + 3; // Center it below the boss
		double positionY = boss2.getLayoutY() + boss2.getTranslateY() + 85; // Adjust the Y position
		secondBossHealthDisplay.setLayout(positionX, positionY);
	}

	public void secondBossUpdateBossHealth(int bossHealth) {
		secondBossHealthDisplay.updateBossHealth(bossHealth);
	}
}
