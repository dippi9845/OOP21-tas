package main.java.tas.controller.enemy;

import java.util.List;
import java.util.Optional;

import main.java.tas.model.enemy.Enemy;

/**
 * An interface for an enemies handler.
 */
public interface EnemiesLogic {

	/**
	 * Moves an enemy from the queue list to the alive list.
	 * 
	 * @return returns the enemy
	 */
	Optional<Enemy> spawnEnemy() throws IndexOutOfBoundsException;

	/**
	 * Removes the given enemy from the alive enemy list.
	 * 
	 * @param enemy that will be removed
	 * @throws NoSuchFieldException if the given enemy is not in the alive list
	 */
	void removeEnemy(final Enemy enemy) throws NoSuchFieldException;

	/**
	 * Checks there are no enemies alive.
	 * 
	 * @return true if the wave is clean
	 */
	boolean isWaveClean();

	/**
	 * @return the wave counter
	 */
	int getWave();

	/**
	 * Increase the wave counter and generates the enemies queue.
	 */
	void setNextWave();

	/**
	 * @return a list with the enemies that are alive
	 */
	List<Enemy> getEnemies();

	/**
	 * @return true if there is at least one enemy alive
	 */
	boolean areEnemiesAlive();

	/**
	 * 
	 * @return true if there is at least one enemy that has not been spawned
	 */
	boolean areEnemiesInQueue();

}
