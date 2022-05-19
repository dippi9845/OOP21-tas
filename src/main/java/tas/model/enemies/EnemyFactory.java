package main.java.tas.model.enemies;

import java.util.List;

/**
 * An interface for
 */
public interface EnemyFactory {
    
    /**
     * Generates a list with some enemies given the wave
     * @param wave of the game
     * @return list of enemies
     */
    List<Enemy> createEnemiesToBeSpawn(int wave);

}
