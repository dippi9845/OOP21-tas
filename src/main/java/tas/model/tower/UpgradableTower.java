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
	 * @return the cost for the next upgrade
	 */
	public int costUpgrade();

	/**
	 * Checks if the tower is upgradable
	 * 
	 * @param money the current disponibility of money by the player
	 * @return True if the tower is upgradable, false otherwise
	 */
	public boolean upgradable(final int money);

	/**
	 * Upgrade the damage to the next level
	 */
	public void upgradeDamage();

}
