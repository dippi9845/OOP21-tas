package main.java.tas.model.tower;

import java.util.Optional;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class BasicTower extends AbstractBasicTower {
	private Optional<Enemy> target = Optional.empty();
	
	protected BasicTower(final Position pos, final int damage, final int radius, final int delay, final int cost) {
		super(pos, damage, radius, delay, cost);
	}

	@Override
	protected void attack() {
		this.target.ifPresent(x->x.dealDamage(getDamage()));
	}

	@Override
	protected void setTarget(final Enemy e) {
		this.target = Optional.ofNullable(e);
	}

	@Override
	public void compute() throws InterruptedException {
		if (this.target.isPresent() && Towers.isTargetInRange(this.target.get(), this)) {
			this.attack();
			Thread.sleep(this.getDelay());
		}
		else {
			Towers.findFirstEnemyInRange(this).ifPresent(this::setTarget);
		}

	}

}
