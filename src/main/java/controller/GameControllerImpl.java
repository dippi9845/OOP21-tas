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
        
        spawnEnemy();
    }
    
    private void spawnEnemy() {
        Enemy enemy = this.enemiesHandler.spawnTestEnemy();
        this.gameScene.getGameView().addEntityLabel(enemy);
    }
    
    private void killEnemy(Enemy enemy) {
        this.gameScene.getGameView().removeEntityLabel(enemy);
        this.enemiesHandler.removeEnemy(enemy);
    }
    
    private void enemyCheck() {
        int i = 0;
        while (i < enemiesHandler.getEnemies().size()) {
            Enemy enemy = enemiesHandler.getEnemies().get(i);
            if (!enemy.isDead()) {
                enemy.moveForward();
                this.gameScene.getGameView().drawEntity(enemy);
            } else {
                killEnemy(enemy);
            }
            i++;
        }
    }
    
    @Override
    public void nextTick() {
        if (!this.enemiesHandler.isWaveClean()) {
            enemyCheck();
        }
    }
    
}
