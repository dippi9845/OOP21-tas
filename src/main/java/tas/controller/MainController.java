package main.java.tas.controller;


import main.java.tas.view.MainViewImpl;

/**
 * Interface that models the project controller.
 */
public interface MainController {

	/**
	 * The main loop of the game, that connect the models with their respective
	 * views.
	 */
	void mainLoop();

	/**
	 * Connects the menu model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createMenu(MainViewImpl view);

	/**
	 * Connects the game model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createGame(MainViewImpl view);

	/**
	 * Connects the sandbox mode model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createSandBoxMode(MainViewImpl view);
	
	/**
	 * Connects the level select model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createLevelSelect(MainViewImpl view);
	
	/**
	 * Connects the end game menu model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createEndGame(MainViewImpl view);
	
	/**
	 * Connects the settings model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createSettings(MainViewImpl view);
	
	/**
	 * Connects the levels full menu model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createFullLevels(MainViewImpl view);
	
	/**
	 * @return the SceneController that is in use.
	 */
	SceneController getController();

}
