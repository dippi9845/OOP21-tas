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
    SceneController createMenu(final MainView view);

    /**
     * Connects the game model, with it's own view.
     * 
     * @param view the main window
     * @return the scene that was created
     */
    SceneController createGame(final MainView view);

    /**
     * @return the SceneController that is in use.
     */
    SceneController getController();

}
