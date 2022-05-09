package main.java.tas.controller;

import java.util.Arrays;

import main.java.tas.model.GameModel;
import main.java.tas.model.GameModelImpl;
import main.java.tas.model.TimeCurve;
import main.java.tas.model.TimeCurveImpl;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;
import main.java.tas.view.GameScene;

/**
 * Class that implements {@link SceneController}
 */
public class GameController implements SceneController {
    
    private final GameScene gameScene;
    private final EnemiesLogic enemiesHandler;
    private final GameModel playerStats;
    private final TimeCurve timer = new TimeCurveImpl();
    
    /**
     * Constructor that creates a game controller for the given game scene
     * @param scene the graphic scene controller
     */
    public GameController(final GameScene scene) {
        this.gameScene = scene;
        
        this.playerStats = new GameModelImpl(100, 150);
        this.enemiesHandler = new EnemiesLogicImpl(Arrays.asList(new Position(500, 500), new Position(750, 750), new Position(0, 1000)));
        //TODO: manca l'inserimento dinamico della posizione dello spawner e altro...
    }
    
    /**
     * Spawns the enemy from the queue and creates the graphic component for it
     */
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
    
    /**
     * Removes the given enemy from the alive list and deletes it's graphical component
     * @param enemy
     */
    private void killEnemy(Enemy enemy) {
        this.gameScene.getGameView().removeEntityLabel(enemy);
        try {
            this.enemiesHandler.removeEnemy(enemy);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Increases the wave count
     */
    private void increaseWave() {
        this.enemiesHandler.setNextWave();
    }
    
    /**
     * Loops over all enemies and moves them. It also checks if they are dead
     * or if they completed their path.
     */
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
    
    /** {@inheritDoc} */
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
