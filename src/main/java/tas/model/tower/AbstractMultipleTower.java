package main.java.tas.model.tower;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.utils.Position;

/**
 * An abstract class that model a Tower with an undefined number of enemies
 * targeted.
 */
public abstract class AbstractMultipleTower extends AbstractBasicTower {
	private final List<Enemy> enemyList;
	private final int maxEnemy;

	/**
	 * Constructor, protected
	 * 
	 * @param pos       Tower position
	 * @param damage    Tower damage
	 * @param radius    Tower radius, where it can attack enemies
	 * @param delay     Tower delay
	 * @param cost      Tower cost
	 * @param towerName Tower name
	 * @param enemyList List of all enemy in the map
	 * @param maxTarget Max number of target that this tower can handle at the time
	 */
	public AbstractMultipleTower(final Position pos, final int damage, final int radius, final int delay,
			final int cost, final String towerName, final List<Enemy> enemyList, final int maxTarget) {
		super(pos, damage, radius, delay, cost, towerName, enemyList);
		this.maxEnemy = maxTarget;
		this.enemyList = new LinkedList<Enemy>();
	}

	/**
	 * Check if an enemy is contained, from the internal list of target
	 * 
	 * @param e Enemy to check if is contained
	 * @return true if the enemy is contained, false otherwise
	 */
	protected boolean contains(final Enemy e) {
		return this.enemyList.contains(e);
	}

	/**
	 * Return the current target list
	 * 
	 * @return list of current enemies targeted
	 */
	protected List<Enemy> getEnemyList() {
		return Collections.unmodifiableList(this.enemyList);
	}

	/**
	 * Return the maximum enemy to be targeted by the tower
	 * 
	 * @return the maximum enemy to be targeted by the tower
	 */
	protected int getMaxEnemy() {
		return this.maxEnemy;
	}

	/**
	 * Check if the list is full
	 * 
	 * @return true if the
	 */
	protected boolean isFull() {
		return this.enemyList.size() == this.maxEnemy;
	}

	/**
	 * Removes an enemy from the enemies targeted
	 * 
	 * @param e Enemy to be removed
	 */
	protected void remove(final Enemy e) {
		this.enemyList.remove(e);
	}

	/**
	 * Clear enemy targeted list
	 */
	protected void clear() {
		this.enemyList.clear();
	}

	/** {@inheritDoc} */
	@Override
	protected void attack() {
		this.enemyList.forEach(x -> Towers.dealDamage(x, this.getDamage()));
	}

	/** {@inheritDoc} */
	@Override
	protected void setTarget(final Enemy e) {
		this.enemyList.add(e);
	}

	/**
	 * Check if an enemy is a valid target for this tower
	 * 
	 * @param e Enemy
	 * @return True if the enemy e is valid as target
	 */
	protected abstract boolean isValidTarget(final Enemy e);
}
