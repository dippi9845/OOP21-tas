package main.java.view;

import main.java.controller.Controller;

public interface GameScene {

    void setObserver(Controller controller);
    GameView getView();
     
}
