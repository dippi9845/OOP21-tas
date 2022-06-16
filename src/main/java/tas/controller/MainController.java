package main.java.tas.controller;


import main.java.tas.view.MainView;

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
	SceneController createMenu(MainView view);

	/**
	 * Connects the game model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createGame(MainView view);

	/**
	 * Connects the sandbox mode model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createSandBoxMode(MainView view);
	
	/**
	 * Connects the level select model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createLevelSelect(MainView view);
	
	/**
	 * Connects the end game menu model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createEndGame(MainView view);
	
	/**
	 * Connects the settings model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createSettings(MainView view);
	
	/**
	 * Connects the levels full menu model, with it's own view.
	 * 
	 * @param view the main window
	 * @return the scene that was created
	 */
	SceneController createFullLevels(MainView view);
	
	/**
	 * @return the SceneController that is in use.
	 */
	SceneController getController();

}
