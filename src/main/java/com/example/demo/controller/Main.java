package com.example.demo.controller;

import com.example.demo.controller.inGame.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;

/**
 * The Main class is the entry point of the Sky Battle game application.
 * It extends the JavaFX Application class and sets up the main stage of the game,
 * including the title, size, and menu screen.
 */
public class Main extends Application {

	/**
	 * The width of the application window.
	 */
	public static final int SCREEN_WIDTH = 1300;

	/**
	 * The height of the application window.
	 */
	public static final int SCREEN_HEIGHT = 750;

	/**
	 * The title of the application window.
	 */
	private static final String TITLE = "Sky Battle";

	/**
	 * The main menu screen of the game.
	 */
	private MainMenu mainMenu;

	/**
	 * The start method is called when the application is launched. It sets up the
	 * primary stage of the JavaFX application, including the title, size, and
	 * the main menu screen.
	 *
	 * @param stage the primary stage of the application.
	 * @throws ClassNotFoundException If a class cannot be found.
	 * @throws NoSuchMethodException If a method cannot be found.
	 * @throws SecurityException If there is a security violation.
	 * @throws InstantiationException If an error occurs while creating an instance of a class.
	 * @throws IllegalAccessException If there is an illegal access to a class or method.
	 * @throws IllegalArgumentException If there is an illegal argument passed to a method.
	 * @throws InvocationTargetException If the method invoked throws an exception.
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		mainMenu = new MainMenu(stage);
		mainMenu.display();
	}

	/**
	 * The main method is the entry point of the application. It launches the JavaFX
	 * application by calling the launch method.
	 *
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		launch();
	}
}
