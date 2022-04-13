package main.java.tas.tower;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbstractBasicTower implements Tower{
	private Enemy target;
	private final int damage;
	private final Position pos;
	private final int radius;
	
	AbstractBasicTower(final Position pos, final int damage, final int radius) {
		this.damage = damage;
		this.pos = pos;
		this.radius = radius;
	}
	
	abstract protected void attack();

	abstract protected void setTarget(Enemy e);
	
	abstract public void compute();
	
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
