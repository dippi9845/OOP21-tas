package main.java.tas.tower;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbstractMultipleTower extends AbstractBasicTower implements Tower {
	private final List<Enemy> enemyList = new LinkedList<>(); // TODO forse meglio un arraylist
	private final int maxEnemy;
	
	protected AbstractMultipleTower(final Position pos, final int damage, final int radius, final int delay, final int cost, final int maxTarget) {
		super(pos, damage, radius, delay, cost);
		this.maxEnemy = maxTarget;
	}

	protected boolean contains(final Enemy e) {
		return this.enemyList.contains(e);
	}
	
	protected Stream<Enemy> getEnemyStream() {
		return this.enemyList.stream();
	}
	
	protected int getMaxEnemy() {
		return this.maxEnemy;
	}
	
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

	@Override
	abstract public void compute();
}
