package main.java.tas.model.tower.factory;

import java.util.List;
import java.util.function.UnaryOperator;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build gas tower
 * it basically attack every enemy in the range at low delay
 */
public interface GasFactory {
	
	/**
	 * @return the damage of Gas Tower
	 */
	static public int getDamage() {
		return 7;
	}
	
	/**
	 * @return the damage of Gas Tower
	 */
	static public int getRange() {
		return 6;
	}
	
	/**
	 * @return the damage of Gas Tower
	 */
	static public int getDelay() {
		return 100;
	}
	
	/**
	 * @return the damage of Gas Tower
	 */
	static public int getBuildCost() {
		return 275;
	}
	
	/**
	 * @return the damage of Gas Tower
	 */
	static public int getStartUpCost() {
		return 95;
	}
	
	/**
	 * @return the damage of Gas Tower
	 */
	static public int getMaxLevel() {
		return 10;
	}
	
	/**
	 * @return the UnaryOperator that associate for a level an increase of cost of Gas Tower
	 */
	static public UnaryOperator<Integer> getIncreaseCost() {
		return (x->5);
	}
	
	/**
	 * @return the UnaryOperator that associate for a level an increase of damage of Gas Tower
	 */
	static public UnaryOperator<Integer> getIncreaseDamage() {
		return (x->5);
	}
	
	/**
	 * @param imageName image name of the tower
	 * @param enemyList List of all enemy in the map
	 * @return ClassicTowerFactory with all preset for Gas Tower
	 */
	static private ClassicTowerFactory constructor(final String imageName, final List<Enemy> enemyList) {
		return new ClassicTowerFactory(getDamage(), getRange(), getDelay(), getBuildCost(), getStartUpCost(), getMaxLevel(), imageName, enemyList);
	}
	
	/**
	 * @param pos Position of the tower
	 * @param enemyList List of all enemy in the map
	 * @return an Gas Tower upgradable
	 */
	static public Tower gasTower(final Position pos, final List<Enemy> enemyList) {
		return constructor("gas", enemyList)
				   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), Integer.MAX_VALUE)
				   .build();
	}
}
