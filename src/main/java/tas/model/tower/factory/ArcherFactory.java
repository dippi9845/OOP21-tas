package main.java.tas.model.tower.factory;

import java.util.List;
import java.util.function.UnaryOperator;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build archer tower
 */
public interface ArcherFactory {
	
	/**
	 * Return the damage of Archer Tower
	 * @return the damage of Archer Tower
	 */
	static public int getDamage() {
		return 60;
	}
	
	/**
	 * Return the range of Archer Tower
	 * @return the range of Archer Tower
	 */
	static public int getRange() {
		return 14;
	}

	/**
	 * Return the delay of Archer Tower
	 * @return the delay of Archer Tower
	 */
	static public int getDelay() {
		return 500;
	}
	
	/**
	 * Return the build cost of Archer Tower
	 * @return the build cost of Archer Tower
	 */
	static public int getBuildCost() {
		return 200;
	}
	
	/**
	 * Return the start upgrade cost of Archer Tower
	 * @return the start upgrade cost of Archer Tower
	 */
	static public int getStartUpCost() {
		return 100;
	}
	
	/**
	 * Return the max level of Archer Tower
	 * @return the max level of Archer Tower
	 */
	static public int getMaxLevel() {
		return 25;
	}
	
	/**
	 * Return ClassicTowerFactory with all preset for Archer Tower
	 * @param imageName image name of the tower
	 * @param enemyList List of all enemy in the map
	 * @return ClassicTowerFactory with all preset for Archer Tower
	 */
	static private ClassicTowerFactory constructor(final String imageName, final List<Enemy> enemylist) {
		return new ClassicTowerFactory(getDamage(), getRange(), getDelay(), getBuildCost(), getStartUpCost(), getMaxLevel(), imageName, enemylist);
	}

	/**
	 * @return the UnaryOperator that associate for a level an increase of cost of Archer Tower
	 */
	static private UnaryOperator<Integer> getIncreaseCost() {
		return (x->20);
	}
	
	/**
	 * Return the UnaryOperator that associate for a level an increase of damage of Archer Tower
	 * @return the UnaryOperator that associate for a level an increase of damage of Archer Tower
	 */
	static private UnaryOperator<Integer> getIncreaseDamage() {
		return (x->15);
	}
	
	/**
	 * Return an archer with only one target possible, and upgradable
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an archer with only one target possible, and upgradable
	 */
	static public Tower basicArcher(final Position pos, final List<Enemy> enemylist) {
		return constructor("archer", enemylist)
			   .upgradableStarndard(pos, getIncreaseCost(), getIncreaseDamage())
			   .build();
	}
	
	/**
	 * Return an archer with two targets possible, and upgradable
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an archer with two targets possible, and upgradable
	 */
	static public Tower biArcher(final Position pos, final List<Enemy> enemylist) {
		return constructor("biarcher", enemylist)
			   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 2)
			   .build();
	}
	
	/**
	 * Return an archer with three targets possible, and upgradable
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an archer with three targets possible, and upgradable
	 */
	static public Tower triArcher(final Position pos, final List<Enemy> enemylist) {
		return constructor("triarcher", enemylist)
			   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 3)
			   .build();
	}
	
	/**
	 * Return an archer with four targets possible, and upgradable
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an archer with four targets possible, and upgradable
	 */
	static public Tower quadArcher(final Position pos, final List<Enemy> enemylist) {
		return constructor("quadarcher", enemylist)
			   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 4)
			   .build();
	}
}
