package main.java.tas.tower;

import java.util.Optional;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class MortarTower extends AbstractMultipleTower {
	private final int minRange;
	private final int attackRange;
	private Position center;
	
	protected MortarTower(Position pos, int damage, int radius, int delay, int maxTarget, int minRange, int attackRange) {
		super(pos, damage, radius, delay, Integer.MAX_VALUE);
		this.minRange = minRange;
		this.attackRange = attackRange;
	}
	
	private boolean isFirstInRange(final Enemy e) {
		return Towers.isTargetInRange(e, this) &&
			  !Towers.isInRange(e.getPosition(), this.getPos(), minRange);
	}

	@Override
	protected boolean isValidTarget(final Enemy e) {
		return Towers.isInRange(e.getPosition(), this.center, this.attackRange);
	}

	@Override
	public void compute() {
		Optional<Enemy> target = Towers.findFistEnemyByPredicate(this::isFirstInRange);
		if (target.isPresent()) {
			this.center = target.get().getPosition();
			Towers.findAll(this::isValidTarget).forEach(this::setTarget);
			this.attack();
			this.clear();
		}
		// TODO sleep
	}


}
