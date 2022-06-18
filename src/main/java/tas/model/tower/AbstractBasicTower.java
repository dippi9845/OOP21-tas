package main.java.tas.model.tower;

import java.util.List;
import main.java.tas.model.enemy.Enemy;
import main.java.tas.utils.Position;

/**
 * This abstract class model a simple tower with some basic methods
 *
 */
public abstract class AbstractBasicTower implements Tower {
	private int damage;
	private final Position pos;
	private final int radius;
	private final int delay;
	private final int cost;
	private final String imageName;
	private final List<Enemy> visibleEnemyList;
	private boolean stopTh;

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
	protected AbstractBasicTower(final Position pos, final int damage, final int radius, final int delay,
			final int cost, final String imageName, final List<Enemy> enemyList) {
		this.damage = damage;
		this.pos = pos;
		this.radius = radius;
		this.delay = delay;
		this.cost = cost;
		this.imageName = imageName;
		this.visibleEnemyList = enemyList;
		this.stopTh = false;
	}

	protected List<Enemy> getVisibleEnemyList() {
		synchronized (this.visibleEnemyList) {
			return List.copyOf(this.visibleEnemyList);
		}
	}

	protected void sleep() {
		try {
			Thread.sleep(this.getDelay());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	public boolean isStop() {
		return this.stopTh;
	}

	/** {@inheritDoc} */
	public void stop() {
		this.stopTh = true;
	}

	/**
	 * Deal damage to the target
	 */
	abstract protected void attack();

	/**
	 * Sets the target of this tower
	 * 
	 * @param e Enemy to be focused
	 */
	abstract protected void setTarget(final Enemy e);

	/**
	 * Increase the damage of the tower, in order to upgrade it
	 * 
	 * @param amount Amount of damage that increase
	 */
	protected void increaseDamage(final int amount) {
		this.damage += damage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDelay() {
		return this.delay;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCost() {
		return this.cost;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDamage() {
		return this.damage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position getPos() {
		return this.pos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRadius() {
		return this.radius;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTowerImageName() {
		return this.imageName;
	}
}
