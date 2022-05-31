package main.java.tas.model.tower.factory;

import java.util.function.UnaryOperator;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * An interface that is specified to build cannon tower
 */
public interface CannonFactory{
	
	/**
	 * Return the damage of Cannon Tower
	 * @return the damage of Cannon Tower
	 */
	static public int getDamage() {
		return 50;
	}
	
	/**
	 * Return the range of Cannon Tower
	 * @return the range of Cannon Tower
	 */
	static public int getRange() {
		return 9;
	}
	
	/**
	 * Return the delay of Cannon Tower
	 * @return the delay of Cannon Tower
	 */
	static public int getDelay() {
		return 500;
	}
	
	/**
	 * Return the build cost of Cannon Tower
	 * @return the build cost of Cannon Tower
	 */
	static public int getBuildCost() {
		return 200;
	}
	
	/**
	 * Return the start upgrade cost of Cannon Tower
	 * @return the start upgrade cost of Cannon Tower
	 */
	static public int getStartUpCost() {
		return 50;
	}
	
	/**
	 * Return the max level of Cannon Tower
	 * @return the max level of Cannon Tower
	 */
	static public int getMaxLevel() {
		return 20;
	}
	
	/**
	 * Return ClassicTowerFactory with all preset for Cannon Tower
	 * @param imageName image name of the tower
	 * @return ClassicTowerFactory with all preset for Cannon Tower
	 */
	static private ClassicTowerFactory constructor(final String imageName) {
		return new ClassicTowerFactory(getDamage(), getRange(), getDelay(), getBuildCost(), getStartUpCost(), getMaxLevel(), imageName);
	}
	
	/**
	 * Return the UnaryOperator that associate for a level an increase of cost of Cannon Tower
	 * @return the UnaryOperator that associate for a level an increase of cost of Cannon Tower
	 */
	static private UnaryOperator<Integer> getIncreaseCost() {
		return (x->5);
	}
	
	/**
	 * Return the UnaryOperator that associate for a level an increase of damage of Cannon Tower
	 * @return the UnaryOperator that associate for a level an increase of damage of Cannon Tower
	 */
	static private UnaryOperator<Integer> getIncreaseDamage() {
		return (x->10);
	}
	
	/**
	 * Return an cannon with only one target possible, and upgradable
	 * @param pos Position of the tower
	 * @return an cannon with only one target possible, and upgradable
	 */
	static public Tower basicCannon(final Position pos) {
		return constructor("cannon")
				   .upgradableStarndard(pos, getIncreaseCost(), getIncreaseDamage())
				   .build();
	}
	
	/**
	 * Return an cannon with two targets possible, and upgradable
	 * @param pos Position of the tower
	 * @return an cannon with two targets possible, and upgradable
	 */
	static public Tower biCannon(final Position pos) {
		return constructor("bicannon")
				   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 2)
				   .build();
	}
	
	/**
	 * Return  an cannon with three targets possible, and upgradable
	 * @param pos Position of the tower
	 * @return  an cannon with three targets possible, and upgradable
	 */
	static public Tower triCannon(final Position pos) {
		return constructor("tricannon")
				   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 3)
				   .build();
	}
	
	/**
	 * Return an cannon with four targets possible, and upgradable
	 * @param pos Position of the tower
	 * @return an cannon with four targets possible, and upgradable
	 */
	static public Tower quadCannon(final Position pos) {
		return constructor("quadcannon")
				   .nTargetStandard(pos, getIncreaseCost(), getIncreaseDamage(), 4)
				   .build();
	}
	
	
}
