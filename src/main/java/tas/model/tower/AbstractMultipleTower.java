package main.java.tas.model.tower;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

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
	
	protected void remove(final Enemy e) {
		this.enemyList.remove(e);
	}

	protected void clear() {
		enemyList.clear();
	}

	@Override
	protected void attack() {
		enemyList.forEach(x->x.dealDamage(this.getDamage()));
	}

	@Override
	protected void setTarget(final Enemy e) {
		this.enemyList.add(e);
	}

	protected abstract boolean isValidTarget(final Enemy e);
}
