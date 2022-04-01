package main.java.controller;

import main.java.model.EnemiesHandler;
import main.java.model.EnemiesHandlerImpl;
import main.java.model.enemies.Enemy;
import main.java.utils.Position;
import main.java.view.GameScene;

public class GameControllerImpl extends GameController {
    
    final GameScene gameScene;
    private final EnemiesHandler enemiesHandler;
    
    public GameControllerImpl(final GameScene scene) {
        this.gameScene = scene;
        
        this.enemiesHandler = new EnemiesHandlerImpl(new Position(500, 500));
        //TODO: manca l'inserimento dinamico della posizione dello spawner e altro...
        
    }
    
    private void spawnEnemy() {
        Enemy enemy = this.enemiesHandler.spawnTestEnemy();
        this.gameScene.getGameView().addEntityLabel(enemy);
    }
    
    @Override
    public void nextTick() {
        if (this.enemiesHandler.cleanWave()) {  //TODO: questa condizione verra cambiata
            spawnEnemy();
        }
        
        this.enemiesHandler.moveEnemies();
        this.gameScene.getGameView().redrawEntities();
    }
    
}
