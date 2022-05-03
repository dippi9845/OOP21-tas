package main.java.tas.controller;

import main.java.tas.view.MainView;

public interface MainController {

    void mainLoop();
    SceneController createMenu(final MainView view);
    SceneController createGame(final MainView view);
    SceneController getController();

}
