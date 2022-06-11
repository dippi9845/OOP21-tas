package main.java.tas.model;

/**
 * An interface for a Game Model.
 */
public interface GameModel {

	/**
	 * Increases the amount of money.
	 * 
	 * @param money the amount by which it will be increased
	 */
	void giveMoney2Player(final int money);

	/**
	 * Deals an amount of damage to the player.
	 * 
	 * @param damage the damage that will be dealt
	 */
	void dealDamage2Player(final int damage);

	/**
	 * @return the health of the player
	 */
	int getHP();

	/**
	 * @return the amount of money that the player has
	 */
	int getPlayerMoney();

	/**
	 * Decreases the amount of money.
	 * 
	 * @param amount amount by which it will be decreased
	 * @return true if the transaction went correctly, false otherwise
	 */
	boolean spendMoney(final int amount);

}
