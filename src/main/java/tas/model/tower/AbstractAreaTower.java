package main.java.tas.model.tower;

import java.util.List;
import java.util.Optional;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.utils.Position;

/**
 * An abstract tower that need only a method to be instanced
 * {@link AbstractAreaTower#firstTarget()} This class model a tower that one it
 * found an enemy, all the other near will be attacked
 */
public abstract class AbstractAreaTower extends AbstractMultipleTower {
	private final int attackRange;
	private Position targetPos;

	/**
	 * Constructor, protected
	 * 
	 * @param pos         Tower position
	 * @param damage      Tower damage
	 * @param radius      Tower radius, where it can attack enemies
	 * @param delay       Tower delay
	 * @param cost        Tower cost
	 * @param towerName   Tower name
	 * @param enemyList   List of all enemy in the map
	 * @param maxTarget   Max number of target that this tower can attack by area at
	 *                    the time
	 * @param attackRange Range of attack given by the first target
	 */
	public AbstractAreaTower(final Position pos, final int damage, final int radius, final int delay, final int cost,
			final String towerName, final List<Enemy> enemyList, final int maxTarget, final int attackRange) {
		super(pos, damage, radius, delay, cost, towerName, enemyList, maxTarget);
		this.attackRange = attackRange;
	}

	/**
	 * Return position of the last target fist target
	 * 
	 * @return position of the last target fist target
	 */
	protected Position getTargetPosition() {
		return this.targetPos;
	}

	/**
	 * Set the position of the targeted target
	 * 
	 * @param pos Position of the target to be set
	 */
	protected void setTargetPosition(final Position pos) {
		this.targetPos = pos;
	}

	/**
	 * {@inheritDoc} Checks if the enemy e is near to the target choose by the tower
	 */
	@Override
	protected boolean isValidTarget(final Enemy e) {
		return Towers.isInRange(e.getPosition(), this.getTargetPosition(), this.attackRange);
	}

	/**
	 * Target all the enemies nearby the first target
	 */
	private void addNearbyTarget() {
		Towers.findAll(this::isValidTarget, this.getVisibleEnemyList())
			.stream()
			.limit(this.getMaxEnemy() - this.getEnemyList().size())
			.forEach(this::setTarget);
	}

	/**
	 * Abstract method, delegate to concrete class the decision to choose the first
	 * target
	 * 
	 * @return an optional of enemy that is empty if the first target was not found
	 */
	abstract protected Optional<Enemy> firstTarget();

	/** {@inheritDoc} */
	@Override
	public void compute() {
		Optional<Enemy> target = this.firstTarget();
		if (target.isPresent()) {
			this.setTargetPosition(target.get().getPosition());
			this.addNearbyTarget();
			this.attack();
			this.clear();
		}
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "AbstractAreaTower [attackRange=" + attackRange + ", targetPos=" + targetPos + ", getEnemyList()="
				+ getEnemyList() + ", getMaxEnemy()=" + getMaxEnemy() + ", isFull()=" + isFull() + ", getDelay()="
				+ getDelay() + ", getCost()=" + getCost() + ", getDamage()=" + getDamage() + ", getPos()=" + getPos()
				+ ", getRadius()=" + getRadius() + ", getTowerName()=" + getTowerName() + "]";
	}

}
