package main.java.tas.tower;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbstractBasicTower implements Tower{
	private final int damage;
	private final Position pos;
	private final int radius;
	private final int delay;
	private final int cost;
	
	protected AbstractBasicTower(final Position pos, final int damage, final int radius, final int delay, final int cost) {
		this.damage = damage;
		this.pos = pos;
		this.radius = radius;
		this.delay = delay;
		this.cost = cost;
	}

	abstract protected void attack();
	
	abstract protected void setTarget(final Enemy e);

	abstract public void compute();

	@Override
	public int getDelay() {
		return this.delay;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	@Override
	public Position getPos() {
		return this.pos;
	}

	@Override
	public int getRadius() {
		return this.radius;
	}
}
