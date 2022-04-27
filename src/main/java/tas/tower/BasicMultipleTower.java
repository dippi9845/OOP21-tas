package main.java.tas.tower;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class BasicMultipleTower extends AbstractMultipleTower {
	
	protected BasicMultipleTower(Position pos, int damage, int radius, int delay, int cost, int maxTarget) {
		super(pos, damage, radius, delay, cost, maxTarget);
	}
	
	@Override
	protected boolean isValidTarget(final Enemy e) {
		return (!this.isFull()
				&& !this.contains(e)
				&& Towers.isTargetInRange(e, this));
	}
	
	@Override
	public void compute() {
		if (!this.isFull()) {
			Towers.findAll(this::isValidTarget)
				  .stream()
				  .limit(this.getMaxEnemy() - this.getEnemyStream().count())
				  .forEach(this::setTarget);
		}
		
		this.getEnemyStream()
			.filter(x->!Towers.isTargetInRange(x, this))
			.forEach(this::remove);
		
		this.attack();
		// TODO sleep
	}

}
