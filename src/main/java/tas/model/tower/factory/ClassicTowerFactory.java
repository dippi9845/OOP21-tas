package main.java.tas.model.tower.factory;

import java.util.List;
import java.util.function.UnaryOperator;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.AttackType;
import main.java.tas.model.tower.Builder;
import main.java.tas.utils.Position;

/**
 * This class is used to avoid repetition by the interfaces Factory,
 * like ArcherFactory, CannonFactory, FlameFactory, GasFactory
 */
class ClassicTowerFactory {

	private final int damage;
	private final int range;
	private final int delay;
	private final int buildCost;
	private final int startUpCost;
	private final int maxLevel;
	private final String imageName;
	private final List<Enemy> enemyList;
	
	/**
	 * Constructor
	 * @param damage damage of the Tower
	 * @param range range of the Tower
	 * @param delay delay of the Tower
	 * @param buildCost build cost of the Tower
	 * @param startUpCost start upgrade cost of the Tower
	 * @param maxLevel maximum level of the Tower
	 * @param imageName image name of the Tower
	 * @param enemyList List of all enemy in the map
	 */
	protected ClassicTowerFactory(final int damage, final int range, final int delay, final int buildCost, final int startUpCost, final int maxLevel, final String imageName, final List<Enemy> enemyList) {
		this.damage = damage;
		this.range = range;
		this.delay = delay;
		this.buildCost = buildCost;
		this.startUpCost = startUpCost;
		this.maxLevel = maxLevel;
		this.imageName = imageName;
		this.enemyList = enemyList;
	}
	
	/**
	 * @return damage of the Tower
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * @return range of the Tower
	 */
	public int getRange() {
		return this.range;
	}

	/**
	 * @return delay delay of the Tower
	 */
	public int getDelay() {
		return this.delay;
	}

	/**
	 * @return build cost of the Tower
	 */
	public int getBuildCost() {
		return this.buildCost;
	}

	/**
	 * @return start upgrade cost of the Tower
	 */
	public int getStartUpCost() {
		return this.startUpCost;
	}

	/**
	 * @return maximum level of the Tower
	 */
	public int getMaxLevel() {
		return this.maxLevel;
	}

	/**
	 * @return image name of the Tower
	 */
	public String getImageName() {
		return this.imageName;
	}

	/**
	 * @param pos position of the tower
	 * @return builder with all preset already set
	 */
	public Builder basicStarndard(final Position pos) {
		return new Builder(pos, this.getDamage(), this.getRange(), this.getDelay(), this.getImageName(), this.enemyList)
				   .cost(this.getBuildCost());
	}
	

	/** 
	 * @param pos Position of the tower
	 * @param upCost the UnaryOperator that associate for a level an increase of cost
	 * @param upDamage the UnaryOperator that associate for a level an increase of damage
	 * @return builder with all preset already set
	 */
	public Builder upgradableStarndard(final Position pos, final UnaryOperator<Integer> upCost, final UnaryOperator<Integer> upDamage) {
		return this.basicStarndard(pos)
				   .setUpgradable(true)
				   .maxLevel(this.getMaxLevel())
				   .upgradeCost(upCost)
				   .upgradeDamage(upDamage)
				   .startUpgradeCost(this.getStartUpCost());
	}
	
	/**
	 * 
	 * @param pos Position of the tower
	 * @param upCost upCost the UnaryOperator that associate for a level an increase of cost
	 * @param upDamage the UnaryOperator that associate for a level an increase of damage
	 * @param n number of maximum target for the tower
	 * @return builder with all preset already set
	 */
	public Builder nTargetStandard(final Position pos, final UnaryOperator<Integer> upCost, final UnaryOperator<Integer> upDamage, final int n) {
		return this.upgradableStarndard(pos, upCost, upDamage)
				   .attackType(AttackType.MULTIPLE)
				   .maximumTarget(n);
	}
}
