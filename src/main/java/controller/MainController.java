package main.java.controller;

import main.java.view.MainView;

public interface MainController {
    
    void mainLoop();
    Controller createMenu(final MainView view);
    Controller createGame(final MainView view);

}
