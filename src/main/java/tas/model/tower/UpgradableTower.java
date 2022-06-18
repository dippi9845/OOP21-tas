package main.java.tas.model.tower;

/**
 * An Interface that model a tower that can be upgraded the damage
 *
 */
public interface UpgradableTower extends Tower {

	/**
	 * @return the current level of upgrade
	 */
	public int getLevel();

	/**
	 * Checks if the tower is time to update
	 * @return True if the tower is time to upgrade it, false otherwise
	 */
	public boolean isTimeToUpgrade();

}
