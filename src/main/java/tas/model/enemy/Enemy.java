package main.java.tas.model.enemy;

import main.java.tas.model.Entity;

/**
 * An interface for an enemy.
 */
public interface Enemy extends Entity {

	/**
	 * Moves the enemy one step forward.
	 */
	void moveForward();

	/**
	 * Deals damage to the enemy.
	 * 
	 * @param damage the damage that will be dealt
	 */
	void dealDamage(final double damage);

	/**
	 * @return True if the enemy is dead
	 */
	boolean isDead();

	/**
	 * @return the health of the enemy
	 */
	double getHealth();

	/**
	 * @return the amount of money that the enemy can drop
	 */
	int getMoney();

	/**
	 * @return the amount of damage that the enemy can deal
	 */
	int getDamage();

	/**
	 * @return True if the enemy has completed its path
	 */
	boolean isPathCompleted();

}
