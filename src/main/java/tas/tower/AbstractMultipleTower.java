package main.java.tas.tower;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbstractMultipleTower extends AbstractBasicTower implements Tower {
	private final List<Enemy> enemyList = new LinkedList<>(); // TODO forse meglio un arraylist
	private final int maxEnemy;
	
	protected abstract boolean isValidTarget(final Enemy e);
	
	protected List<Enemy> getEnemyList() {
		return this.enemyList;
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
	
	protected AbstractMultipleTower(Position pos, int damage, int radius, int delay, final int maxTarget) {
		super(pos, damage, radius, delay);
		this.maxEnemy = maxTarget;
	}

	@Override
	protected void attack() {
		enemyList.forEach(x->x.dealDamage(this.getDamage()));
	}

	@Override
	protected void setTarget(final Enemy e) {
		this.enemyList.add(e);
	}

	@Override
	abstract public void compute();

}
