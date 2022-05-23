package main.java.tas.model.tower;

import java.util.function.UnaryOperator;
import main.java.tas.utils.Position;

public class UpgradableTowerImpl implements UpgradableTower{
	
	private final AbstractBasicTower tower;
	private int upgradecost;
	private final UnaryOperator<Integer> increasedamage;
	private final UnaryOperator<Integer> increasecost;
	private int level;
	private final int maxLevel;
	
	protected UpgradableTowerImpl(final AbstractBasicTower tower, final UnaryOperator<Integer> increasedamage, final UnaryOperator<Integer> increasecost,  final int startCostUpgrade, final int maxLevel) {
		this.tower = tower;
		this.increasedamage = increasedamage;
		this.increasecost = increasecost;
		this.upgradecost = startCostUpgrade;
		this.level = 1;
		this.maxLevel = maxLevel;
	}
	
	private boolean levelUnderMax() {
		return this.getLevel() < this.maxLevel;
	}

	@Override
	public void compute() throws InterruptedException {
		this.tower.compute();
	}

	@Override
	public int getDamage() {
		return this.tower.getDamage();
	}

	@Override
	public int getRadius() {
		return this.tower.getRadius();
	}

	@Override
	public int getCost() {
		return this.tower.getCost();
	}

	@Override
	public int getDelay() {
		return this.tower.getDelay();
	}

	@Override
	public Position getPos() {
		return this.tower.getPos();
	}

	@Override
	public int costUpgrade() {
		return this.upgradecost;
	}

	@Override
	public String getTowerImageName() {
		return this.tower.getImageName();
	}

	@Override
	public void upgradeDamage() {
		if (this.levelUnderMax()) {
			this.tower.increaseDamage(this.increasedamage.apply(this.getLevel()));
			this.upgradecost += this.increasecost.apply(this.getLevel());
			this.level++;
		}
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public boolean upgradable(final int money) {
		return this.costUpgrade() <= money && this.levelUnderMax();
	}

}
