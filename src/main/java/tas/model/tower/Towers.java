package main.java.tas.model.tower;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

/**
 * A collections of functions used by all towers to help them to achieve some tasks,
 * avoid some repetitions and abstract from other implementations
 */
public class Towers {
	
	private Towers() {}
	
	/**
	 * List of all current alive enemies
	 */
	static private Optional<List<Enemy>> ENEMYLIST = Optional.empty();

	/**
	 * Initialize ENEMYLIST only one time.
	 * here is where is called the one time {@link main.java.tas.controller.TowerLogicImpl#TowerLogicImpl(List, java.util.function.Consumer, Predicate)}.
	 * @param enemyList list of enemy
	 * @throws IllegalStateException if the method is called when the list was already initialized
	 */
	static public void initEnemyList(final List<Enemy> enemyList) throws IllegalStateException{
		if (ENEMYLIST.isEmpty()) {
			ENEMYLIST = Optional.ofNullable(enemyList);
		}
		else {
			throw new IllegalStateException("Enemy list was already initialized");
		}
	}

	/**
	 * returns ENEMYLIST if is initialized
	 * @return list of enemies
	 * @throws IllegalStateException if is not initialized
	 */
	static public List<Enemy> getEnemyList() throws IllegalStateException{
		if (ENEMYLIST.isEmpty()) {
			throw new IllegalStateException("Enemy list is not initialized, you have to initialize it by calling initEnemyList");
		}
		else {
			return ENEMYLIST.get();
		}
	}
	
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
	 * Find the first enemy in the list that satisfies the predicate given
	 * @param f Predicate to be satisfied
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the enemy found
	 */
	static public Optional<Enemy> findFistEnemyByPredicate(final Predicate<Enemy> f) {
		return getEnemyList().stream().filter(f).findFirst();
	}
	
	/**
	 * Find the first enemy in the list {@link Towers#ENEMYLIST}, that satisfies two given predicates
	 * @param f The first predicate
	 * @param g the second predicate
	 * @return Optional of enemy, that is empty if no enemy was found, otherwise the enemy found
	 */
	static public Optional<Enemy> findFistEnemyBiPredicate(final Predicate<Enemy> f, final Predicate<Enemy> g) {
		return getEnemyList().stream().filter(f).filter(g).findFirst();
	}
	
	/**
	 * Find all enemies that satisfies the predicate given, by the list {@link Towers#ENEMYLIST}
	 * @param f predicate to be satisfied
	 * @return List containing all the enemies that satisfies the predicate
	 */
	static public List<Enemy> findAll(final Predicate<Enemy> f) {
		return getEnemyList().stream().filter(f).collect(Collectors.toList());
	}
}