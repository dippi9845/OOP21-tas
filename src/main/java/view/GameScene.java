package main.java.view;

import main.java.controller.Controller;

public interface GameScene {

    void resize();
    void setObserver(Controller controller);
    GameView getGameView();
     
}
