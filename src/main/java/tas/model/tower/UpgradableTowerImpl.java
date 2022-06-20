package main.java.tas.model.tower;

import java.util.function.UnaryOperator;
import main.java.tas.utils.Position;

/**
 * A class that implements the interface {@link UpgradableTower}
 * It takes an {@link AbstractBasicTower} and with delegation implements all the Tower
 * interface methods. The {@link AbstractBasicTower} taken will be upgradable in damage
 */
public class UpgradableTowerImpl implements UpgradableTower{

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
	 * @param tower            Tower which will be upgradable
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
	 * Increase the damage by the given rule {@link UpgradableTowerImpl#increasedamage}
	 * Increase the cost of upgrade by the given rule {@link UpgradableTowerImpl#increasecost}
	 * Increase the current level by one
	 */
	private void upgradeDamage() {
		this.tower.increaseDamage(this.increasedamage.apply(this.getLevel()));
		System.out.print(this.upgradecountcost + " + " + this.increasecost.apply(this.upgradecountcost) + " = ");
		this.upgradecountcost = this.increasecost.apply(this.upgradecountcost);
		System.out.println(this.upgradecountcost);
		this.level++;
	}

	/** 
	 * {@inheritDoc}
	 * the computation is delegate to internal tower,
	 * next the counter of computation is increased,
	 * when {@link UpgradableTowerImpl#isTimeToUpgrade()},
	 * the counter will be reseted, and the tower will
	 * perform an upgrade
	 */
	@Override
	public void compute() {
		this.tower.compute();
		this.computecount++;
		
		if (this.isTimeToUpgrade()) {
			this.computecount = 0;
			this.upgradeDamage();
		}
	}
	
	/**
	 * Check if the current level is under the maximum
	 * 
	 * @return True if the current level is under the maximum, False otherwise
	 */
	public boolean levelUnderMax() {
		return this.getLevel() < this.maxLevel;
	}
	
	/**
	 * {@inheritDoc}
	 * If the compute count is equal to the upgrade count cost and the max level is not reached
	 * is time to upgrade the tower
	 */
	@Override
	public boolean isTimeToUpgrade() {
		return this.computecount == this.upgradecountcost && this.levelUnderMax();
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

	/** {@inheritDoc} */
	@Override
	public boolean isStop() {
		return this.tower.isStop();
	}

	/** {@inheritDoc} */
	@Override
	public void stop() {
		this.tower.stop();
	}

	

	

}
