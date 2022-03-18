package main.java.controller;

import main.java.view.GameSceneImpl;

public class GameControllerImpl extends GameController {
    
    final GameSceneImpl view;
    
    public GameControllerImpl(final GameSceneImpl scene) {
        this.view = scene;
        
    }

}
