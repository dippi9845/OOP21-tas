package main.java.tas.controller;

import java.util.Arrays;

import main.java.tas.model.EnemiesFactory;
import main.java.tas.model.EnemiesFactoryImpl;
import main.java.tas.model.GameModel;
import main.java.tas.model.GameModelImpl;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;
import main.java.tas.view.GameScene;

public class GameController implements Controller {
    
    private final GameScene gameScene;
    private final EnemiesFactory enemiesHandler;
    private final GameModel playerStats;
    
    public GameController(final GameScene scene) {
        this.gameScene = scene;
        
        this.playerStats = new GameModelImpl();
        this.enemiesHandler = new EnemiesFactoryImpl(Arrays.asList(new Position(500, 500), new Position(750, 750), new Position(0, 1000)));
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
