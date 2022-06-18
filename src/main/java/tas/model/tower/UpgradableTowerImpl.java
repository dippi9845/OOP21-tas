package main.java.tas.model.tower;

import java.util.function.UnaryOperator;
import main.java.tas.utils.Position;

/**
 * A class that implements the interface UpgradableTower {@link UpgradableTower}
 * It takes an AbstracBasicTower and with delegation implements all the Tower
 * interface methods
 */
public class UpgradableTowerImpl implements UpgradableTower {

	private final AbstractBasicTower tower;
	private int computecount;
	private int upgradecountcost;
	private final UnaryOperator<Integer> increasedamage;
	private final UnaryOperator<Integer> increasecost;
	private int level;
	private final int maxLevel;

	/**
	 * Constructor, protected
	 * 
	 * @param tower            Tower which can be upgradable
	 * @param increasedamage   UnaryOperator that delegate the increase of damage of
	 *                         the tower
	 * @param increasecost     UnaryOperator that delegate the increase of cost of
	 *                         the tower
	 * @param startCostUpgrade the stating cost of upgrade
	 * @param maxLevel         maximum level that the tower can reach
	 */
	protected UpgradableTowerImpl(final AbstractBasicTower tower, final UnaryOperator<Integer> increasedamage,
			final UnaryOperator<Integer> increasecost, final int startCostUpgrade, final int maxLevel) {
		this.tower = tower;
		this.increasedamage = increasedamage;
		this.increasecost = increasecost;
		this.upgradecountcost = startCostUpgrade;
		this.level = 1;
		this.computecount = 0;
		this.maxLevel = maxLevel;
	}

	/**
	 * Check if the current level is under the maximum
	 * 
	 * @return True if the current level is under the maximum, False otherwise
	 */
	public boolean levelUnderMax() {
		return this.getLevel() < this.maxLevel;
	}

	/** {@inheritDoc} */
	@Override
	public void compute() {
		this.tower.compute();
		this.computecount++;
		
		if (this.isTimeToUpgrade()) {
			this.computecount = 0;
			this.upgradeDamage();
		}
	}

	/** {@inheritDoc} */
	@Override
	public int getDamage() {
		return this.tower.getDamage();
	}

	/** {@inheritDoc} */
	@Override
	public int getRadius() {
		return this.tower.getRadius();
	}

	/** {@inheritDoc} */
	@Override
	public int getCost() {
		return this.tower.getCost();
	}

	/** {@inheritDoc} */
	@Override
	public int getDelay() {
		return this.tower.getDelay();
	}

	/** {@inheritDoc} */
	@Override
	public Position getPos() {
		return this.tower.getPos();
	}

	/** {@inheritDoc} */
	@Override
	public String getTowerImageName() {
		return this.tower.getImageName();
	}

	private void upgradeDamage() {
		this.tower.increaseDamage(this.increasedamage.apply(this.getLevel()));
		this.upgradecountcost += this.increasecost.apply(this.getLevel());
		this.level++;
	}

	/** {@inheritDoc} */
	@Override
	public int getLevel() {
		return this.level;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "UpgradableTower [tower=" + tower + ", upgradecost=" + upgradecountcost + ", increasedamage=" + increasedamage
				+ ", increasecost=" + increasecost + ", level=" + level + ", maxLevel=" + maxLevel + ", getDamage()="
				+ getDamage() + ", getRadius()=" + getRadius() + ", getCost()=" + getCost() + ", getDelay()="
				+ getDelay() + ", getPos()=" + getPos() + ", getTowerImageName()="
				+ getTowerImageName() + ", getLevel()=" + getLevel() + "]";
	}

	@Override
	public boolean isStop() {
		return this.tower.isStop();
	}

	@Override
	public void stop() {
		this.tower.stop();
	}

	@Override
	public boolean isTimeToUpgrade() {
		return this.computecount == this.upgradecountcost && this.levelUnderMax();
	}

	

}
