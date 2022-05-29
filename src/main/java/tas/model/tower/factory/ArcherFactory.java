package main.java.tas.model.tower.factory;

import java.util.function.UnaryOperator;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build archer tower
 */
public interface ArcherFactory {
	
	/**
	 * @return the damage of Archer Tower
	 */
	static public int getDamage() {
		return 60;
	}
	
	/**
	 * @return the range of Archer Tower
	 */
	static public int getRange() {
		return 14;
	}

	/**
	 * @return the delay of Archer Tower
	 */
	static public int getDelay() {
		return 500;
	}
	
	/**
	 * @return the build cost of Archer Tower
	 */
	static public int getBuildCost() {
		return 200;
	}
	
	/**
	 * @return the start upgrade cost of Archer Tower
	 */
	static public int getStartUpCost() {
		return 100;
	}
	
	/**
	 * @return the max level of Archer Tower
	 */
	static public int getMaxLevel() {
		return 25;
	}
	
	/**
	 * @param imageName image name of the tower
	 * @return ClassicTowerFactory with all preset for Archer Tower
	 */
	static private ClassicTowerFactory constructor(final String imageName) {
		return new ClassicTowerFactory(getDamage(), getRange(), getDelay(), getBuildCost(), getStartUpCost(), getMaxLevel(), imageName);
	}

	/**
	 * @return the UnaryOperator that associate for a level an increase of cost of Archer Tower
	 */
	static private UnaryOperator<Integer> getIncreaseCost() {
		return (x->20);
	}
	
	/**
	 * @return the UnaryOperator that associate for a level an increase of damage of Archer Tower
	 */
	static private UnaryOperator<Integer> getIncreaseDamage() {
		return (x->15);
	}
	
	/**
	 * @param pos Position of the tower
	 * @return an archer with only one target possible, and upgradable
	 */
	static public Tower basicArcher(final Position pos) {
		return constructor("archer")
			   .upgradableStarndard(pos, getIncreaseCost(), getIncreaseDamage())
			   .build();
	}
	
	/**
	 * @param pos Position of the tower
	 * @return an archer with two targets possible, and upgradable
	 */
	static public Tower biArcher(final Position pos) {
		return constructor("biarcher")
			   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 2)
			   .build();
	}
	
	/**
	 * @param pos Position of the tower
	 * @return an archer with three targets possible, and upgradable
	 */
	static public Tower triArcher(final Position pos) {
		return constructor("triarcher")
			   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 3)
			   .build();
	}
	
	/**
	 * @param pos Position of the tower
	 * @return an archer with four targets possible, and upgradable
	 */
	static public Tower quadArcher(final Position pos) {
		return constructor("quadarcher")
			   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 4)
			   .build();
	}
}
