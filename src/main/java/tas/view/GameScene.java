package main.java.tas.view;

import main.java.tas.controller.Controller;

public interface GameScene {

    void setObserver(Controller controller);
    GameView getGameView();
     
}
