package main.java.controller;

import main.java.model.EnemiesHandlerImpl;
import main.java.model.Position;
import main.java.view.GameSceneImpl;

public class GameControllerImpl extends GameController {
    
    final GameSceneImpl view;
    private final EnemiesHandlerImpl enemiesHandler;
    
    public GameControllerImpl(final GameSceneImpl scene) {
        this.view = scene;
        
        this.enemiesHandler = new EnemiesHandlerImpl(new Position(100, 100));
        //TODO: manca l'inserimento della posizione dello spawner e altro...
        
    }

    @Override
    public void nextTick() {
        this.enemiesHandler.moveEnemies();
    }
    
}
