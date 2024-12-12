package com.example.demo.level.levelview;

import com.example.demo.view.LevelThreeDisplay;
import com.example.demo.view.KillCountDisplay;
import javafx.scene.Group;

public class LevelViewLevelThree extends LevelView {

	private final Group root;
	private final KillCountDisplay killCountDisplay;
	private final LevelThreeDisplay levelThreeDisplay;

	public LevelViewLevelThree(Group root, int heartsToDisplay, int killsToAdvance) {
		super(root, heartsToDisplay);
		this.root = root;
		this.killCountDisplay = new KillCountDisplay(killsToAdvance);
		this.levelThreeDisplay = new LevelThreeDisplay();
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

	public void showLevelThree () {
		root.getChildren().add(levelThreeDisplay);
	}

	public void hideLevelThree () {
		levelThreeDisplay.hideLevelThreeText();
	}
}
