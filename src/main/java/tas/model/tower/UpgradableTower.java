package main.java.tas.model.tower;

/**
 * An Interface that model a tower that can be upgraded
 *
 */
public interface UpgradableTower extends Tower {

	/**
	 * Return the current level of upgrade
	 * @return the current level of upgrade
	 */
	public int getLevel();

	/**
	 * Checks if the tower can be upgraded
	 * @return True if the tower is time to upgrade it, false otherwise
	 */
	public boolean isTimeToUpgrade();

}
