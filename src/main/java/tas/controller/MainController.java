package main.java.tas.controller;

import main.java.tas.view.MainView;

public interface MainController {

    void mainLoop();
    Controller createMenu(final MainView view);
    Controller createGame(final MainView view);
    Controller getController();

}
