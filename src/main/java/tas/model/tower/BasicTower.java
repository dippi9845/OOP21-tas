package main.java.tas.model.tower;

import java.util.List;
import java.util.Optional;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

/**
 * This concrete class model a Simple Tower with just one target
 *
 */
public class BasicTower extends AbstractBasicTower {

	private Optional<Enemy> target = Optional.empty();

	/**
	 * Constructor, protected
	 * 
	 * @param pos       Tower position
	 * @param damage    Tower damage
	 * @param radius    Tower radius, where it can attack enemies
	 * @param delay     Tower delay
	 * @param cost      Tower cost
	 * @param imageName Tower image name
	 * @param enemyList List of all enemy in the map
	 */
	protected BasicTower(final Position pos, final int damage, final int radius, final int delay, final int cost,
			final String imageName, final List<Enemy> enemyList) {
		super(pos, damage, radius, delay, cost, imageName, enemyList);
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

	/** {@inheritDoc} */
	@Override
	public void compute() throws InterruptedException {
		if (this.target.isPresent() && Towers.isTargetInRange(this.target.get(), this) && !this.target.get().isDead()) {
			this.attack();
			Thread.sleep(this.getDelay());
		} else {
			Towers.findFirstEnemyInRange(this, this.getVisibleEnemyList()).ifPresent(this::setTarget);
		}

	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BasicTower [target=" + target + ", getDelay()=" + getDelay() + ", getCost()=" + getCost()
				+ ", getDamage()=" + getDamage() + ", getPos()=" + getPos() + ", getRadius()=" + getRadius()
				+ ", getTowerImageName()=" + getTowerImageName() + "]";
	}

}
