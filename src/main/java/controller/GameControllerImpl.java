package main.java.controller;

import main.java.model.GameModel;
import main.java.view.GameSceneImpl;

public class GameControllerImpl extends GameController {
    
    final GameSceneImpl view;
    final GameModel game;
    
    public GameControllerImpl(final GameSceneImpl scene) {
        this.view = scene;
        this.game = new GameModel();    //TODO: manca l'inserimento della posizione dello spawner e altro...
        
    }

    @Override
    public void nextTick() {
        this.game.moveEnemies();
    }
    
}
