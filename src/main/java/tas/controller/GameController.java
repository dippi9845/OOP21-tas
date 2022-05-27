package main.java.tas.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.awt.Color;

import main.java.tas.model.GameModel;
import main.java.tas.model.TimeCurve;
import main.java.tas.model.TimeCurveImpl;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;
import main.java.tas.view.GameSceneImpl;


/**
 * Class that implements {@link SceneController}
 */
public class GameController implements SceneController {
    
    private final GameSceneImpl gameScene;
    private final EnemiesLogic enemiesHandler;
    private final GameModel playerStats;
    private final TimeCurve timer = new TimeCurveImpl((x) -> (int)(10/(x+1.5) + 1));
    private final Color pathColor = new Color(255, 255, 255);
    private final int pathThickness = 50;
    private final TowerLogic towerLogic;
    
    // TODO Aggiungere metodo per aggiungere una torre (this.towerLogic.buildTower(final Tower t))
    
    /**
     * Constructor that creates a game controller for the given game scene
     * @param scene the graphic scene controller
     */
    public GameController(final GameSceneImpl scene, GameModel gameModel) {
        this.gameScene = scene;
        
        this.playerStats = gameModel;
        
        List<Position> pathNodes = Arrays.asList(new Position(500, 500), new Position(750, 750), new Position(0, 1000));
        this.enemiesHandler = new EnemiesLogicImpl(pathNodes);
        ((GameSceneImpl)this.gameScene).getGameView().getGamePanel().setLine(pathNodes, pathColor, pathThickness);
        
        this.towerLogic = new TowerLogicImpl(this.enemiesHandler.getEnemies(), this.gameScene.getGameView().getGamePanel()::addEntity, this.playerStats::spendMoney);
        
        //TODO: manca l'inserimento dinamico della posizione dello spawner e altro...
    }
    
    /**
     * Spawns the enemy from the queue and creates the graphic component for it
     */
    private void spawnEnemies() {
        if (!this.timer.isTimeForAction(this.enemiesHandler.getWave())) {
            return;
        }

        Optional<Enemy> enemy = this.enemiesHandler.spawnEnemy();
        if (enemy.isPresent()) {
            this.gameScene.getGameView().addEntityLabel(enemy.get());
        }
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
