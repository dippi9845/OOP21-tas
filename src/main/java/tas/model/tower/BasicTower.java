package main.java.tas.model.tower;

import java.util.List;
import java.util.Optional;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.utils.Position;

/**
 * This concrete class model a simple Tower with just one target, to attack
 *
 */
public class BasicTower extends AbstractBasicTower {

	private Optional<Enemy> target;
	
	/**
	 * Constructor, protected
	 * 
	 * @param pos       Tower position
	 * @param damage    Tower damage
	 * @param radius    Tower radius, where it can attack enemies
	 * @param delay     Tower delay
	 * @param cost      Tower cost
	 * @param towerName Tower name
	 * @param enemyList List of all enemy in the map
	 */
	public BasicTower(final Position pos, final int damage, final int radius, final int delay, final int cost,
			final String towerName, final List<Enemy> enemyList) {
		super(pos, damage, radius, delay, cost, towerName, enemyList);
		this.target = Optional.empty();
	}

	/** {@inheritDoc} */
	@Override
	protected void attack() {
		this.target.ifPresent(x -> Towers.dealDamage(x, this.getDamage()));
	}

	/** {@inheritDoc} */
	@Override
	protected void setTarget(final Enemy e) {
		this.target = Optional.ofNullable(e);
	}

	/**
	 * {@inheritDoc}
	 *
	 * This tower first checks if it found an enemy before, then checks if is still
	 * in range, then checks if is not dead, if there 3 conditions are satisfied the
	 * target is still valid so can be attacked. Otherwise the tower try to seek one
	 * enemy from the list {@link AbstractBasicTower#getVisibleEnemyList()}
	 */
	@Override
	public void compute() {
		if (this.target.isPresent() && Towers.isTargetInRange(this.target.get(), this) && !this.target.get().isDead()) {
			this.attack();
		} else {
			Towers.findFirstEnemyInRange(this, this.getVisibleEnemyList()).ifPresent(this::setTarget);
		}

		this.sleep();
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BasicTower [target=" + target + ", getDelay()=" + getDelay() + ", getCost()=" + getCost()
				+ ", getDamage()=" + getDamage() + ", getPos()=" + getPos() + ", getRadius()=" + getRadius()
				+ ", getTowerName()=" + getTowerName() + "]";
	}

}
