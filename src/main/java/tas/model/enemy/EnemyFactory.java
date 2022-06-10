package main.java.tas.model.enemy;

import java.util.List;

/**
 * An interface for an enemy factory.
 */
public interface EnemyFactory {

	/**
	 * Generates a list with some enemies given the wave.
	 * 
	 * @param wave of the game
	 * @return list of enemies
	 */
	List<Enemy> createEnemiesToBeSpawn(int wave);

}
