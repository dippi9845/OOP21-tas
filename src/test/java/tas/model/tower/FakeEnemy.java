package test.java.tas.model.tower;

import main.java.tas.utils.Size;

import main.java.tas.model.enemy.Enemy;
import main.java.tas.utils.Position;

/**
 * A class that is only used by this package to make tests abstracting from the
 * implementation of the interface Enemy, this enemy just get damage, and can be
 * changed the position
 */
class FakeEnemy implements Enemy {
	private Position pos;
	private double health;

	@SuppressWarnings("unused")
	private FakeEnemy() {
	};

	protected FakeEnemy(final Position pos, final double health) {
		this.pos = pos;
		this.health = health;
	}

	public void setPosition(final Position pos) {
		this.pos = pos;
	}

	@Override
	public Position getPosition() {
		return this.pos;
	}

	@Override
	public Size getBodyDimension() {
		return new Size(10, 10);
	}

	@Override
	public String getEntityName() {
		return "Niente Nome";
	}

	@Override
	public void moveForward() {
	}

	@Override
	public void dealDamage(final double damage) {
		if (!this.isDead()) {
			this.health -= damage;
		}
	}

	@Override
	public boolean isDead() {
		return this.health < 0.01;
	}

	@Override
	public double getHealth() {
		return this.health;
	}

	@Override
	public int getMoney() {
		return 0;
	}

	@Override
	public int getDamage() {
		return 0;
	}

	@Override
	public boolean isPathCompleted() {
		return true;
	}

}
