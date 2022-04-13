package main.java.tas.tower;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class Tower {
	private Enemy target;
	private final int damage;
	private final Position pos;
	private final int radius;
	
	Tower(final Position pos, final int damage, final int radius) {
		this.damage = damage;
		this.pos = pos;
		this.radius = radius;
	}
	
	abstract protected void attack();
	
	protected boolean isValidTarget(final Enemy e) {
		if (Position.findDistance(this.pos, e.getPosition()) <= this.radius) {
			return true;
		}
		else {
			return false;
		}
	}

	abstract protected void setTarget(Enemy e);
	
	abstract public void compute();
}
