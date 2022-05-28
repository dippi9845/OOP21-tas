package main.java.tas.model.tower;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

/**
 * A collections of functions used by all towers to help them to achieve some tasks and avoid some repetitions
 */
public class Towers {
	
	private Towers() {}
	
	/**
	 * List of all current alive enemies
	 *
	 * Initialized in {@link main.java.tas.controller.TowerLogicImpl#TowerLogicImpl(List, java.util.function.Consumer, Predicate)}
	 * and never change it
	 */
	static public List<Enemy> ENEMYLIST;
	
	/**
	 * Check if the distance between two position is under the radius
	 * @param x first position
	 * @param y second position
	 * @param radius range
	 * @return True if the distance is under radius
	 */
	static public boolean isInRange(final Position x, final Position y, final int radius) {
		return Position.findDistance(x, y) <= radius;
	}
	
	/**
	 * Check if an enemy is in range for a given tower
	 * @param e the enemy
	 * @param t the tower
	 * @return True if the enemy is in the range of the tower
	 */
	static public boolean isTargetInRange(final Enemy e, final Tower t) {
		return isInRange(e.getPosition(), t.getPos(), t.getRadius());
	}
	
	/**
	 * Find the first enemy in range in the list {@link Towers#ENEMYLIST}
	 * @param t Position of the tower
	 * @param radius radius of the tower
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the enemy found
	 */
	static public Optional<Enemy> findFirstEnemyInRange(final Position t, final int radius) {
		return findFistEnemyByPredicate(e->Towers.isInRange(e.getPosition(), t, radius));
	}
	
	/**
	 * Find the first enemy in range in the list {@link Towers#ENEMYLIST}
	 * @param t Tower of reference
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the enemy found
	 */
	static public Optional<Enemy> findFirstEnemyInRange(final Tower t) {
		return findFistEnemyByPredicate(x->Towers.isTargetInRange(x, t));
	}
	
	/**
	 * Find the first enemy in the list {@link Towers#ENEMYLIST}, that satisfies the predicate given
	 * @param f Predicate to be satisfied
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the enemy found
	 */
	static public Optional<Enemy> findFistEnemyByPredicate(final Predicate<Enemy> f) {
		return ENEMYLIST.stream().filter(f).findFirst();
	}
	
	/**
	 * Find the first enemy in the list {@link Towers#ENEMYLIST}, that satisfies two given predicates
	 * @param f The first predicate
	 * @param g the second predicate
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the enemy found
	 */
	static public Optional<Enemy> findFistEnemyBiPredicate(final Predicate<Enemy> f, final Predicate<Enemy> g) {
		return ENEMYLIST.stream().filter(f).filter(g).findFirst();
	}
	
	/**
	 * Find all enemies that satisfies the predicate given, by the list {@link Towers#ENEMYLIST}
	 * @param f predicate to be satisfied
	 * @return List containing all the enemies that satisfies the predicate
	 */
	static public List<Enemy> findAll(final Predicate<Enemy> f) {
		return ENEMYLIST.stream().filter(f).collect(Collectors.toList());
	}
}