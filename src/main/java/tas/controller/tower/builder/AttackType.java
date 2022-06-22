package main.java.tas.controller.tower.builder;

/**
 * This enumeration is used to specify the attack type of the tower that we want
 * to build
 */
public enum AttackType {
	/**
	 * A single enemy will be attacked
	 */
	BASIC,
	/**
	 * A defined number of enemy will be attacked
	 */
	MULTIPLE,
	/**
	 * All the enemy nearby one will be attacked
	 */
	AREA
}
