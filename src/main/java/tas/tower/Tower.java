package main.java.tas.tower;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class Tower {
	private Enemy target;
	private final int damage;
	private final Position pos;
	private final int radius;
	private final int wait;
	
	Tower(final Position pos, final int damage, final int radius, final int wait) {
		this.damage = damage;
		this.pos = pos;
		this.radius = radius;
		this.wait = wait;
	}
	
	
	
}
