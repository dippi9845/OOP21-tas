package main.java.tas.tower;

import java.util.LinkedList;
import java.util.List;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbsractMultipleTower extends AbstractBasicTower implements Tower {
	final List<Enemy> enemyList = new LinkedList<>(); // TODO forse meglio un arraylist
	final int maxEnemy;
	
	private boolean isFull() {
		return this.enemyList.size() == this.maxEnemy;
	}
	
	private boolean isValidTarget(final Enemy e) {
		return (!this.isFull() && !this.enemyList.contains(e) && Towers.isValidTarget(e, this));
	}
	
	protected AbsractMultipleTower(Position pos, int damage, int radius, int delay, final int maxTarget) {
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
	public void compute() {
		if (!this.isFull()) {
			List<Enemy> toAdd = Towers.findAll(this::isValidTarget);
			toAdd.stream().limit(this.maxEnemy - this.enemyList.size()).forEach(this::setTarget);
		}
		// TODO rimuovere i nemici lontani
		this.attack();
		// TODO sleep
	}

}