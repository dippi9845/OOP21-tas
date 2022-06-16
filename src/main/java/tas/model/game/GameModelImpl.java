package main.java.tas.model.game;

/**
 * Class that implements {@link GameModel}.
 */
public class GameModelImpl implements GameModel {

	private int playerHP;
	private int playerMoney;
	private int playerPoints;

	/**
	 * Constructor that creates the game model and set up the player attributes.
	 * 
	 * @param playerMaxHealth     the initial player's health
	 * @param playerStartingMoney the initial player's money
	 */
	public GameModelImpl(final int playerMaxHealth, final int playerStartingMoney) {
		this.playerHP = playerMaxHealth;
		this.playerMoney = playerStartingMoney;
		this.playerPoints = 0;
	}
	
	/** {@inheritDoc} */
	@Override
	public int getPoints() {
		return this.playerPoints;
	}
	
	/** {@inheritDoc} */
	@Override
	public void giveMoney2Player(final int money) {
		this.playerMoney += money;
	}

	/** {@inheritDoc} */
	@Override
	public void dealDamage2Player(final int damage) {
		this.playerHP -= damage;
	}

	/** {@inheritDoc} */
	@Override
	public int getHP() {
		return this.playerHP;
	}

	/** {@inheritDoc} */
	@Override
	public int getPlayerMoney() {
		return this.playerMoney;
	}

	/** {@inheritDoc} */
	public boolean spendMoney(final int amount) {
		if (this.playerMoney >= amount) {
			this.playerMoney -= amount;
			return true;
		} else {
			return false;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void increasePoint() {
		this.playerPoints++;
	}

}
