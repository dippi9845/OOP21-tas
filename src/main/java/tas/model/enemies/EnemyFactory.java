package main.java.tas.model.enemies;

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
