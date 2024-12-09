package com.example.demo.level.levelview;

import com.example.demo.view.KillCountDisplay;
import javafx.scene.Group;

public class LevelViewLevelThree extends LevelView {

	private final Group root;
	private final KillCountDisplay killCountDisplay;

	public LevelViewLevelThree(Group root, int heartsToDisplay, int killsToAdvance) {
		super(root, heartsToDisplay);
		this.root = root;
		this.killCountDisplay = new KillCountDisplay(killsToAdvance);
	}

	public void showKillCount() {
		root.getChildren().add(killCountDisplay);
	}

	public void updateKillCount(int killCount) {
		killCountDisplay.updateKillCount(killCount);
	}
}
