package main.java.tas.model.tower.factory;

import java.util.List;
import java.util.function.UnaryOperator;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build Flame tower
 * it has a low delay for damage
 */
public interface FlameFactory {
	
	/**
	 * @return the damage of Flame Tower
	 */
	static public int getDamage() {
		return 10;
	}
	
	/**
	 * @return the range of Flame Tower
	 */
	static public int getRange() {
		return 6;
	}
	
	/**
	 * @return the delay of Flame Tower
	 */
	static public int getDelay() {
		return 100;
	}
	
	/**
	 * @return the build cost of Flame Tower
	 */
	static public int getBuildCost() {
		return 250;
	}
	
	/**
	 * @return the start upgrade cost of Flame Tower
	 */
	static public int getStartUpCost() {
		return 75;
	}
	
	/**
	 * @return the maximum level of Flame Tower
	 */
	static public int getMaxLevel() {
		return 10;
	}
	
	/**
	 * @return the UnaryOperator that associate for a level an increase of cost of Flame Tower
	 */
	static private UnaryOperator<Integer> getIncreaseCost() {
		return (x->5);
	}
	
	/**
	 * @return the UnaryOperator that associate for a level an increase of damage of Flame Tower
	 */
	static private UnaryOperator<Integer> getIncreaseDamage() {
		return (x->5);
	}
	
	/**
	 * @param imageName image name of the tower
	 * @param enemyList List of all enemy in the map
	 * @return ClassicTowerFactory with all preset for Flame Tower
	 */
	static private ClassicTowerFactory constructor(final String imageName, final List<Enemy> enemylist) {
		return new ClassicTowerFactory(getDamage(), getRange(), getDelay(), getBuildCost(), getStartUpCost(), getMaxLevel(), imageName, enemylist);
	}
	
	/**
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with only one target possible, and upgradable
	 */
	static public Tower basicFlame(final Position pos, final List<Enemy> enemylist) {
		return constructor("flame", enemylist)
				   .upgradableStarndard(pos, getIncreaseCost(), getIncreaseDamage())
				   .build();
	}
	
	/**
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with two targets possible, and upgradable
	 */
	static public Tower biFlame(final Position pos, final List<Enemy> enemylist) {
		return constructor("biflame", enemylist)
			   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 2)
			   .build();
	}
	
	/**
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with three targets possible, and upgradable
	 */
	static public Tower triFlame(final Position pos, final List<Enemy> enemylist) {
		return constructor("triflame", enemylist)
				   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 3)
				   .build();
	}
	
	/**
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Flame with four targets possible, and upgradable
	 */
	static public Tower quadFlame(final Position pos, final List<Enemy> enemylist) {
		return constructor("quadflame", enemylist)
				   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 4)
				   .build();
	}
}
