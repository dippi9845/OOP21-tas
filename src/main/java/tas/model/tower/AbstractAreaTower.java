package main.java.tas.model.tower;

import java.util.Optional;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

/**
 * An abstract tower that need only a method to be instanced {@link AbstractAreaTower#firstTarget()} 
 * This class model a tower that one it found an enemy, all the other near will be attacked
 */
public abstract class AbstractAreaTower extends AbstractMultipleTower {
	private final int attackRange;
	private Position targetPos;
	
	/**
	 * Constructor, protected
	 * @param pos Tower position
	 * @param damage Tower damage
	 * @param radius Tower radius, where it can attack enemies
	 * @param delay Tower delay
	 * @param cost Tower cost
	 * @param imageName  Tower image name
	 * @param maxTarget Max number of target that this tower can attack by area at the time
	 * @param attackRange Range of attack given by the first target
	 */
	protected AbstractAreaTower(final Position pos, final int damage, final int radius, final int delay, final int cost, final String imageName, final int maxTarget, final int attackRange) {
		super(pos, damage, radius, delay, cost, imageName, maxTarget);
		this.attackRange = attackRange;
	}
	
	/**
	 * @return position of the last target
	 */
	protected Position getTagetPosition() {
		return this.targetPos;
	}

	/**
	 * Set the position of the targeted target
	 * @param pos Position of the target to be set
	 */
	protected void setTargetPosition(final Position pos) {
		this.targetPos = pos;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean isValidTarget(final Enemy e) {
		return Towers.isInRange(e.getPosition(), this.targetPos, this.attackRange);
	}

	/**
	 * Target all the enemies nearby the first target
	 */
	private void addNearbyTarget() {
		Towers.findAll(this::isValidTarget).forEach(this::setTarget);
	}

	/**
	 * Abstract method, delegate to concrete class the decision to choose the first target
	 * @return an optional of enemy that is empty if the first target was not found
	 */
	abstract protected Optional<Enemy> firstTarget();

	/** {@inheritDoc} */
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

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "AbstractAreaTower [attackRange=" + attackRange + ", targetPos=" + targetPos + ", getEnemyList()="
				+ getEnemyList() + ", getMaxEnemy()=" + getMaxEnemy() + ", isFull()=" + isFull() + ", getDelay()="
				+ getDelay() + ", getCost()=" + getCost() + ", getDamage()=" + getDamage() + ", getPos()=" + getPos()
				+ ", getRadius()=" + getRadius() + ", getTowerImageName()=" + getTowerImageName() + "]";
	}

}
