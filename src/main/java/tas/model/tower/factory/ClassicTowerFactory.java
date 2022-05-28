package main.java.tas.model.tower.factory;

import java.util.function.UnaryOperator;

import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.Builder;
import main.java.tas.utils.Position;

class ClassicTowerFactory {

	private final int damage;
	private final int range;
	private final int delay;
	private final int buildCost;
	private final int startUpCost;
	private final int maxLevel;
	private final String imageName;
	
	protected ClassicTowerFactory(final int damage, final int range, final int delay, final int buildCost, final int startUpCost, final int maxLevel, final String imageName) {
		this.damage = damage;
		this.range = range;
		this.delay = delay;
		this.buildCost = buildCost;
		this.startUpCost = startUpCost;
		this.maxLevel = maxLevel;
		this.imageName = imageName;
	}
		
	public int getDamage() {
		return this.damage;
	}

	public int getRange() {
		return this.range;
	}

	public int getDelay() {
		return this.delay;
	}

	public int getBuildCost() {
		return this.buildCost;
	}

	public int getStartUpCost() {
		return this.startUpCost;
	}

	public int getMaxLevel() {
		return this.maxLevel;
	}

	public String getImageName() {
		return this.imageName;
	}

	public Builder basicStarndard(final Position pos) {
		return new Builder(pos, this.getDamage(), this.getRange(), this.getDelay(), this.getImageName())
				   .cost(this.getBuildCost());
	}
	

	public Builder upgradableStarndard(final Position pos, final UnaryOperator<Integer> upCost, final UnaryOperator<Integer> upDamage) {
		return this.basicStarndard(pos)
				   .setUpgradable(true)
				   .maxLevel(this.getMaxLevel())
				   .upgradeCost(upCost)
				   .upgradeDamage(upDamage)
				   .startUpgradeCost(this.getStartUpCost());
	}
	
	public Builder nTargetStandard(final Position pos, final UnaryOperator<Integer> upCost, final UnaryOperator<Integer> upDamage, final int n) {
		return this.upgradableStarndard(pos, upCost, upDamage)
				   .attackType(AttackType.MULTIPLE)
				   .maximumTarget(n);
	}
}
