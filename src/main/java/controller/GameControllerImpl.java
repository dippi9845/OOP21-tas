package main.java.controller;

import java.util.Arrays;

import main.java.model.EnemiesHandler;
import main.java.model.EnemiesHandlerImpl;
import main.java.model.GameModel;
import main.java.model.GameModelImpl;
import main.java.model.enemies.Enemy;
import main.java.utils.Position;
import main.java.view.GameScene;

public class GameControllerImpl extends GameController {
    
    private final GameScene gameScene;
    private final EnemiesHandler enemiesHandler;
    private final GameModel playerStats;
    
    public GameControllerImpl(final GameScene scene) {
        this.gameScene = scene;
        
        this.playerStats = new GameModelImpl();
        this.enemiesHandler = new EnemiesHandlerImpl(Arrays.asList(new Position(500, 500), new Position(750, 750), new Position(0, 1000)));
        //TODO: manca l'inserimento dinamico della posizione dello spawner e altro...
        
        spawnEnemy();
    }
    
    private void spawnEnemy() {
        Enemy enemy = this.enemiesHandler.spawnEnemy();
        this.gameScene.getGameView().addEntityLabel(enemy);
    }
    
    private void killEnemy(Enemy enemy) {
        this.gameScene.getGameView().removeEntityLabel(enemy);
        this.enemiesHandler.removeEnemy(enemy);
    }
    
    private void enemiesCheck() {
        for (int i=0; i < enemiesHandler.getEnemies().size(); i++) {
            Enemy enemy = enemiesHandler.getEnemies().get(i);
            
            if (enemy.isDead()) {
                this.playerStats.giveMoney2Player(enemy.getMoney());
                killEnemy(enemy);
                continue;
            }
            if (enemy.hasCompletedPath()) {
                this.playerStats.dealDamage2Player(enemy.getDamage());
                killEnemy(enemy);
                continue;
            }
            
            enemy.moveForward();
            this.gameScene.getGameView().drawEntity(enemy);
        }
    }
    
    @Override
    public void nextTick() {
        if (!this.enemiesHandler.isWaveClean()) {
            enemiesCheck();
        }
    }
    
}
