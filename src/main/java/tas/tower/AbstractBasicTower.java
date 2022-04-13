package main.java.tas.tower;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbstractBasicTower implements Tower{
	private final int damage;
	private final Position pos;
	private final int radius;
	private final int delay;
	
	protected AbstractBasicTower(final Position pos, final int damage, final int radius, final int delay) {
		this.damage = damage;
		this.pos = pos;
		this.radius = radius;
		this.delay = delay;
	}
	
	abstract protected void attack();

	abstract protected void setTarget(final Enemy e);
	
	abstract public void compute();
	
	public int getDelay() {
		return this.delay;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public Position getPos() {
		return this.pos;
	}
	
	public int getRadius() {
		return this.radius;
	}
}
