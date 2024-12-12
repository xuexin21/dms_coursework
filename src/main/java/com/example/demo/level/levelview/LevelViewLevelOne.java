package com.example.demo.level.levelview;

import com.example.demo.view.LevelOneDisplay;
import com.example.demo.view.KillCountDisplay;
import javafx.scene.Group;

public class LevelViewLevelOne extends LevelView {

	private final Group root;
	private final KillCountDisplay killCountDisplay;
	private final LevelOneDisplay levelOneDisplay;

	public LevelViewLevelOne(Group root, int heartsToDisplay, int killsToAdvance) {
		super(root, heartsToDisplay);
		this.root = root;
		this.killCountDisplay = new KillCountDisplay(killsToAdvance);
		this.levelOneDisplay = new LevelOneDisplay();
	}

	public void showKillCount() {
		root.getChildren().add(killCountDisplay);
	}

	public void updateKillCount(int killCount) {
		killCountDisplay.updateKillCount(killCount);
	}

	public void hideKillCount () {
		killCountDisplay.hideKillCount();
	}

	public void showLevelOne () {
		root.getChildren().add(levelOneDisplay);
	}

	public void hideLevelOne () {
		levelOneDisplay.hideLevelOneText();
	}
}
