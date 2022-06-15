package main.java.tas.model.tower;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import main.java.tas.model.enemy.Enemy;
import main.java.tas.utils.Position;

/**
 * A collections of functions used by all towers to help them to achieve some
 * tasks, avoid some repetitions and abstract from other implementations
 */
public class Towers {

	private Towers() {
	}

	/**
	 * Check if the distance between two position is under the radius
	 * 
	 * @param x      first position
	 * @param y      second position
	 * @param radius range
	 * @return True if the distance is under radius
	 */
	static public boolean isInRange(final Position x, final Position y, final int radius) {
		System.out.println(Position.findDistance(x, y));
		return Position.findDistance(x, y) <= radius;
	}

	/**
	 * Check if an enemy is in range for a given tower
	 * 
	 * @param e the enemy
	 * @param t the tower
	 * @return True if the enemy is in the range of the tower
	 */
	static public boolean isTargetInRange(final Enemy e, final Tower t) {
		return isInRange(e.getPosition(), t.getPos(), t.getRadius());
	}

	/**
	 * Find the first enemy in range in the list provided
	 * 
	 * @param t         Position of the tower
	 * @param radius    radius of the tower
	 * @param enemyList List of all enemy in the map
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the
	 *         enemy found
	 */
	static public Optional<Enemy> findFirstEnemyInRange(final Position t, final int radius,
			final List<Enemy> enemyList) {
		return findFistEnemyByPredicate(e -> Towers.isInRange(e.getPosition(), t, radius), enemyList);
	}

	/**
	 * Find the first enemy in range in the list provided
	 * 
	 * @param t         Tower of reference
	 * @param enemyList List of all enemy in the map
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the
	 *         enemy found
	 */
	static public Optional<Enemy> findFirstEnemyInRange(final Tower t, final List<Enemy> enemyList) {
		return findFistEnemyByPredicate(x -> Towers.isTargetInRange(x, t), enemyList);
	}

	/**
	 * Find the first enemy in the list that satisfies the predicate given
	 * 
	 * @param f         Predicate to be satisfied
	 * @param enemyList List of all enemy in the map
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the
	 *         enemy found
	 */
	static public Optional<Enemy> findFistEnemyByPredicate(final Predicate<Enemy> f, final List<Enemy> enemyList) {	
		return enemyList.stream().filter(f).findFirst();
	}

	/**
	 * Find the first enemy in the list provided, that satisfies two given
	 * predicates
	 * 
	 * @param f         The first predicate
	 * @param g         the second predicate
	 * @param enemyList List of all enemy in the map
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the
	 *         enemy found
	 */
	static public Optional<Enemy> findFistEnemyBiPredicate(final Predicate<Enemy> f, final Predicate<Enemy> g,
			final List<Enemy> enemyList) {
		return enemyList.stream().filter(x->!x.isDead()).filter(f).filter(g).findFirst();
	}

	/**
	 * Find all enemies that satisfies the predicate given, by the list provided
	 * 
	 * @param f         predicate to be satisfied
	 * @param enemyList List of all enemy in the map
	 * @return List containing all the enemies that satisfies the predicate
	 */
	static public List<Enemy> findAll(final Predicate<Enemy> f, final List<Enemy> enemyList) {
		List<Enemy> rtr = new LinkedList<Enemy>();
		for (var i : enemyList) {
			if (!i.isDead() && f.test(i)) {
				rtr.add(i);
			}
		}
		return rtr;
		//return enemyList.stream().filter(x->!x.isDead()).filter(f).collect(Collectors.toList());
	}

	/**
	 * Deal damage to an Enemy, in order to abstract from the Enemy implementation
	 * 
	 * @param e      Enemy to deal damage to
	 * @param damage amount of damage
	 */
	static public void dealDamage(final Enemy e, final int damage) {
		e.dealDamage(damage);
	}
}