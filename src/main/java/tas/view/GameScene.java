package main.java.tas.view;

import main.java.tas.controller.SceneController;

public interface GameScene {

    void setObserver(SceneController controller);
    GameView getGameView();
     
}
