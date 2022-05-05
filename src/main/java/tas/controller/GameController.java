package main.java.tas.controller;

import java.util.Arrays;

import main.java.tas.model.GameModel;
import main.java.tas.model.GameModelImpl;
import main.java.tas.model.TimeCurve;
import main.java.tas.model.TimeCurveImpl;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;
import main.java.tas.view.GameScene;

public class GameController implements SceneController {
    
    private final GameScene gameScene;
    private final EnemiesLogic enemiesHandler;
    private final GameModel playerStats;
    private final TimeCurve timer = new TimeCurveImpl();
    
    public GameController(final GameScene scene) {
        this.gameScene = scene;
        
        this.playerStats = new GameModelImpl();
        this.enemiesHandler = new EnemiesLogicImpl(Arrays.asList(new Position(500, 500), new Position(750, 750), new Position(0, 1000)));
        //TODO: manca l'inserimento dinamico della posizione dello spawner e altro...
    }
    
    private void spawnEnemies() {
        if (!this.timer.isTimeForAction(this.enemiesHandler.getWave())) {
            return;
        }
        
        Enemy enemy = this.enemiesHandler.spawnEnemy();
        if (enemy == null) {
            return;
        }
        this.gameScene.getGameView().addEntityLabel(enemy);
        this.timer.actionPerformed();
    }
    
    private void killEnemy(Enemy enemy) {
        this.gameScene.getGameView().removeEntityLabel(enemy);
        try {
            this.enemiesHandler.removeEnemy(enemy);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    
    private void increaseWave() {
        this.enemiesHandler.setNextWave();
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
        if (this.enemiesHandler.isWaveClean()) {
            increaseWave();
        }
        if (this.enemiesHandler.areEnemiesInQueue()) {
            spawnEnemies();
        }
        
        if (this.enemiesHandler.areEnemiesOnBoard()) {
            enemiesCheck();
        }
    }
    
}
