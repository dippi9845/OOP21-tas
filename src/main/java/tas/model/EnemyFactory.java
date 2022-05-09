package main.java.tas.model;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.enemies.RedEnemy;
import main.java.tas.model.enemies.GreenEnemy;
import main.java.tas.model.enemies.PinkEnemy;

/**
 * An interface for an enemy factory
 * 
 */
public interface EnemyFactory {
    
    /**
     * @return a {@link RedEnemy}
     */
    Enemy spawnRedEnemy();
    
    /**
     * @return a {@link GreenEnemy}
     */
    Enemy spawnGreenEnemy();
    
    /**
     * @return a {@link PinkEnemy}
     */
    Enemy spawnPinkEnemy();
    
}
