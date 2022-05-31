package main.java.tas.model.enemies;

/**
 * An interface for an enemy builder.
 * 
 */
public interface EnemyBuilder {

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
