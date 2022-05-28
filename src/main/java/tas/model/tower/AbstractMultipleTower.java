package main.java.tas.model.tower;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

/**
 * An abstract class that model a Tower with an undefined number of enemies targeted
 */
public abstract class AbstractMultipleTower extends AbstractBasicTower implements Tower {
	private final List<Enemy> enemyList = new LinkedList<>();
	private final int maxEnemy;
	/**
	 * Constructor, protected
	 * @param pos Tower position
	 * @param damage Tower damage
	 * @param radius Tower radius, where it can attack enemies
	 * @param delay Tower delay
	 * @param cost Tower cost
	 * @param imageName  Tower image name
	 * @param maxTarget Max number of target that this tower can handle at the time
	 */
	protected AbstractMultipleTower(final Position pos, final int damage, final int radius, final int delay, final int cost, final String imageName, final int maxTarget) {
		super(pos, damage, radius, delay, cost, imageName);
		this.maxEnemy = maxTarget;
	}
	
	/**
	 * Check if an enemy is contained
	 * @param e Enemy to check if is contained
	 * @return true if the enemy is contained, false otherwise
	 */
	protected boolean contains(final Enemy e) {
		return this.enemyList.contains(e);
	}
	
	/**
	 * @return an immutable list of current enemies targeted
	 */
	protected List<Enemy> getEnemyList() {
		return Collections.unmodifiableList(this.enemyList);
	}

	/**
	 * @return the maximum enemy to be targeted by the tower
	 */
	protected int getMaxEnemy() {
		return this.maxEnemy;
	}

	/**
	 * Check if the list is full
	 * @return true if the
	 */
	protected boolean isFull() {
		return this.enemyList.size() == this.maxEnemy;
	}
	
	/**
	 * Removes an enemy to enemies targeted
	 * @param e
	 */
	protected void remove(final Enemy e) {
		this.enemyList.remove(e);
	}

	/**
	 * Clear enemy list
	 */
	protected void clear() {
		this.enemyList.clear();
	}

	/** {@inheritDoc} */
	@Override
	protected void attack() {
		this.enemyList.forEach(x->x.dealDamage(this.getDamage()));
	}
	
	/** {@inheritDoc} */
	@Override
	protected void setTarget(final Enemy e) {
		if (!this.isFull() && !this.contains(e)) {
			this.enemyList.add(e);
		}
	}

	/**
	 * Check if an enemy is a valid target
	 * @param e Enemy
	 * @return True if the enemy e is valid as target
	 */
	protected abstract boolean isValidTarget(final Enemy e);
}
