package main.java.tas.model.tower;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.utils.Position;

/**
 * This class is a basic implementation of {@link AbstractMultipleTower} this
 * BasicMultipleTower model a tower that attack the first maxTarget in the range
 */
public class BasicMultipleTower extends AbstractMultipleTower {

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
	 * @param maxTarget Max number of target that this tower can handle at the time
	 */
	protected BasicMultipleTower(final Position pos, final int damage, final int radius, final int delay,
			final int cost, final String towerName, final List<Enemy> enemyList, final int maxTarget) {
		super(pos, damage, radius, delay, cost, towerName, enemyList, maxTarget);
	}

	/**
	 * {@inheritDoc} The enemy must be in range, the list does not have to be full,
	 * and the enemy can't be added more than one time
	 */
	@Override
	protected boolean isValidTarget(final Enemy e) {
		return Towers.isTargetInRange(e, this) && !this.isFull() && !this.contains(e);
	}

	/**
	 * {@inheritDoc} First this tower checks for all targeted if they are still
	 * valid, so must be in range and still alive, if there is some enemy not valid
	 * will be removed. Second if the list is not full the tower will seek for the
	 * remaining number of enemy. Than every targeted enemy will be attacked
	 */
	@Override
	public void compute() {
		Towers.findAll(x -> !Towers.isTargetInRange(x, this) || x.isDead(), this.getEnemyList()).stream()
				.forEach(this::remove);

		if (!this.isFull()) {
			Towers.findAll(this::isValidTarget, this.getVisibleEnemyList()).stream()
					.limit(this.getMaxEnemy() - this.getEnemyList().size()).forEach(this::setTarget);
		}

		this.attack();
		this.sleep();
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BasicMultipleTower [getEnemyList()=" + getEnemyList() + ", getMaxEnemy()=" + getMaxEnemy()
				+ ", isFull()=" + isFull() + ", getDelay()=" + getDelay() + ", getCost()=" + getCost()
				+ ", getDamage()=" + getDamage() + ", getPos()=" + getPos() + ", getRadius()=" + getRadius()
				+ ", getTowerName()=" + getTowerName() + "]";
	}

}
