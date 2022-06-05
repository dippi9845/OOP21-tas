package main.java.tas.model.enemies;

/**
 * An interface for an enemy builder.
 * 
 */
public interface EnemyBuilder {

    /**
     * @return an enemy of red type
     */
    Enemy spawnRedEnemy();

    /**
     * @return an enemy of green type
     */
    Enemy spawnGreenEnemy();

    /**
     * @return an enemy of pink type
     */
    Enemy spawnPinkEnemy();

}
