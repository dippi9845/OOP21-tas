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
	
	protected AbstractBasicTower(final Position pos, final int damage, final int radius, final int delay, final int cost, final String imageName) {
		this.damage = damage;
		this.pos = pos;
		this.radius = radius;
		this.delay = delay;
		this.cost = cost;
		this.imageName = imageName;
	}

	abstract protected void attack();
	
	abstract protected void setTarget(final Enemy e);

	abstract public void compute() throws InterruptedException;

	protected void increaseDamage(final int amount) {
		this.damage += damage;
	}

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

	@Override
	public String getTowerImageName() {
		return this.imageName;
	}
}
