package main.java.tas.model.tower;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbstractBasicTower implements Tower{
	private int damage;
	private final Position pos;
	private final int radius;
	private final int delay;
	private final int cost;
	private final String imageName;
	/**
	 * Constructor, protected
	 * @param pos Tower position
	 * @param damage Tower damage
	 * @param radius Tower radius, where it can attack enemies
	 * @param delay Tower delay
	 * @param cost Tower cost
	 * @param imageName  Tower image name
	 */
	protected AbstractBasicTower(final Position pos, final int damage, final int radius, final int delay, final int cost, final String imageName) {
		this.damage = damage;
		this.pos = pos;
		this.radius = radius;
		this.delay = delay;
		this.cost = cost;
		this.imageName = imageName;
	}

	/**
	 * Deal damage to the target
	 */
	abstract protected void attack();
	
	/**
	 * Sets the target of this tower
	 * @param e Enemy to be focused
	 */
	abstract protected void setTarget(final Enemy e);

	/**
	 * Increase the damage of the tower, in order to upgrade it
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
