package com.example.demo.level.levelview;

import com.example.demo.model.Boss;
import com.example.demo.view.BossHealthDisplay;
import com.example.demo.view.ShieldImage;
import javafx.scene.Group;

public class LevelViewLevelFour extends LevelView {

	private final Group root;
	private final ShieldImage shieldImage;
	private final BossHealthDisplay bossHealthDisplay;

	public LevelViewLevelFour(Group root, int heartsToDisplay, int bossHealth) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage();
		this.bossHealthDisplay = new BossHealthDisplay(bossHealth);
	}
	
	public void showShield() {
		shieldImage.showShield();
		shieldImage.toFront();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

	public void displayShield() {
		root.getChildren().add(shieldImage);
	}

	public void updateShieldPosition(Boss boss){
		double bossPositionX =  boss.getLayoutX() + boss.getTranslateX() - 10;
		double bossPositionY = boss.getLayoutY() + boss.getTranslateY() + 20;
		shieldImage.setLayout(bossPositionX, bossPositionY);
	}

	public void showBossHealth() {
		root.getChildren().add(bossHealthDisplay);
	}

	public void hideBossHealth() {
		bossHealthDisplay.hideBossHealth();
	}

	public void updateHealthDisplayPosition(Boss boss) {
		double positionX = boss.getLayoutX() + boss.getTranslateX() + 3; // Center it below the boss
		double positionY = boss.getLayoutY() + boss.getTranslateY() + 85; // Adjust the Y position
		bossHealthDisplay.setLayout(positionX, positionY);
	}

	public void updateBossHealth(int bossHealth) {
		bossHealthDisplay.updateBossHealth(bossHealth);
	}
}
