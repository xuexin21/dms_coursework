package com.example.demo.controller;

import com.example.demo.audio.Music;
import com.example.demo.audio.Sound;
import com.example.demo.level.LevelParent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The Controller class handles the flow of the game, including managing the scenes,
 * transitioning between game levels, and interacting with sound and music.
 * It acts as the intermediary between the game's user interface and the game logic.
 */
public class Controller implements Observer {

	/**
	 * The class name for the first level of the game.
	 */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";

	/**
	 * The primary stage of the JavaFX application where scenes are set.
	 */
	private final Stage stage;

	/**
	 * The Music object to control background music playback.
	 */
	private final Music music;

	/**
	 * The Sound object to control sound effects playback.
	 */
	private final Sound sound;

	/**
	 * Constructs a Controller object with the provided stage, music, and sound.
	 *
	 * @param stage the main stage of the game
	 * @param music the Music object to control background music
	 * @param sound the Sound object to control sound effects
	 */
	public Controller(Stage stage, Music music, Sound sound) {
		this.stage = stage;
		this.music = music;
		this.sound = sound;
	}

	/**
	 * Starts the game by showing the main stage and transitioning to the first level.
	 *
	 * @throws ClassNotFoundException if the class for the first level is not found
	 * @throws NoSuchMethodException if the constructor for the first level is not found
	 * @throws SecurityException if there is a security violation during reflection
	 * @throws InstantiationException if an error occurs during instantiation
	 * @throws IllegalAccessException if access to the constructor is denied
	 * @throws IllegalArgumentException if there are illegal arguments for the constructor
	 * @throws InvocationTargetException if the constructor invocation fails
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Transitions to the specified level by dynamically loading the class and
	 * invoking its constructor. It also sets up the scene and starts the game.
	 *
	 * @param className the name of the class for the level to transition to
	 * @throws ClassNotFoundException if the class for the level is not found
	 * @throws NoSuchMethodException if the constructor for the level is not found
	 * @throws SecurityException if there is a security violation during reflection
	 * @throws InstantiationException if an error occurs during instantiation
	 * @throws IllegalAccessException if access to the constructor is denied
	 * @throws IllegalArgumentException if there are illegal arguments for the constructor
	 * @throws InvocationTargetException if the constructor invocation fails
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class, Music.class, Sound.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), music, sound);
		myLevel.addObserver(this);
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();
	}

	/**
	 * This method is called when an observable object (such as a game level) notifies the observer (the controller).
	 * It handles the transition to the next level based on the class name passed in the update.
	 *
	 * @param arg0 the observable object (game level)
	 * @param arg1 the object passed by the observable (the class name of the next level)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}
}
