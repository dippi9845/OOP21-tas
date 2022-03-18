package main.java.controller;

import main.java.view.MainView;

public interface MainController {
    
    Controller createMenu(final MainView view);
    Controller createGame(final MainView view);

}
