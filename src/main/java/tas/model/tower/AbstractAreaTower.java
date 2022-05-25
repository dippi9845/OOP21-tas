package main.java.tas.model.tower;

import java.util.Optional;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbstractAreaTower extends AbstractMultipleTower {
	private final int attackRange;
	private Position targetPos;
	
	protected AbstractAreaTower(final Position pos, final int damage, final int radius, final int delay, final int cost, final String imageName, final int maxTarget, final int attackRange) {
		super(pos, damage, radius, delay, cost, imageName, maxTarget);
		this.attackRange = attackRange;
	}
	
	protected Position getTagetPosition() {
		return this.targetPos;
	}
	
	protected void setTargetPosition(final Position pos) {
		this.targetPos = pos;
	}

	@Override
	protected boolean isValidTarget(final Enemy e) {
		return Towers.isInRange(e.getPosition(), this.targetPos, this.attackRange);
	}
	
	private void addNearbyTarget() {
		Towers.findAll(this::isValidTarget).forEach(this::setTarget);
	}
	
	abstract protected Optional<Enemy> firstTarget();

	@Override
	public void compute() throws InterruptedException {
		Optional<Enemy> target = this.firstTarget();
		if (target.isPresent()) {
			this.targetPos = target.get().getPosition();
			this.addNearbyTarget();
			this.attack();
			this.clear();
		}
		Thread.sleep(this.getDelay());
	}

	@Override
	public String toString() {
		return "AbstractAreaTower [attackRange=" + attackRange + ", targetPos=" + targetPos + ", getEnemyList()="
				+ getEnemyList() + ", getMaxEnemy()=" + getMaxEnemy() + ", isFull()=" + isFull() + ", getDelay()="
				+ getDelay() + ", getCost()=" + getCost() + ", getDamage()=" + getDamage() + ", getPos()=" + getPos()
				+ ", getRadius()=" + getRadius() + ", getTowerImageName()=" + getTowerImageName() + "]";
	}

}
