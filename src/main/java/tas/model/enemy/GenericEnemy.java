package main.java.tas.model.enemy;

import main.java.tas.utils.Size;
import java.util.List;

import main.java.tas.utils.GameSpecs;
import main.java.tas.utils.Position;

/**
 * Class that models an Enemy.
 */
public class GenericEnemy implements Enemy {

	private GameSpecs gameSpecs = new GameSpecs();

	private final Size bodyDimension;
	private final Position actualPosition;
	private double health;
	private final int money;
	private final int damage;
	private final double speed;
	private final String enemyName;

	private int reachedNode;
	private final List<Position> nodesPosition;

	/**
	 * Set up the enemy with the given parameters.
	 * 
	 * @param nodesPosition the nodes that the enemy will have to travel
	 * @param health        the health of the enemy
	 * @param money         the money that the enemy can drop
	 * @param damage        the damage that the enemy can deal
	 * @param speed         the speed of the enemy (pixels/seconds)
	 * @param bodyDimension the dimension of the enemy
	 * @param enemyName     the name of the enemy
	 * @throws IllegalArgumentException if @param nodesPosition is empty
	 */
	public GenericEnemy(final List<Position> nodesPosition, final double health, final int money, final int damage,
			final double speed, final Size bodyDimension, final String enemyName) throws IllegalArgumentException {
		if (nodesPosition.isEmpty()) {
			throw new IllegalArgumentException("@param nodesPosition can't be an empty array!");
		}

		this.nodesPosition = nodesPosition;
		this.reachedNode = 0;
		this.actualPosition = new Position(this.nodesPosition.get(this.reachedNode).getX(),
				this.nodesPosition.get(this.reachedNode).getY());

		this.bodyDimension = bodyDimension;
		this.health = health;
		this.money = money;
		this.damage = damage;
		this.speed = speed / this.gameSpecs.getTickRate();
		this.enemyName = enemyName;
	}

	/** {@inheritDoc} */
	@Override
	public Position getPosition() {
		return this.actualPosition;
	}

	/** {@inheritDoc} */
	@Override
	public Size getBodyDimension() {
		return this.bodyDimension;
	}

	/** {@inheritDoc} */
	@Override
	public void moveForward() {
		double distanceToBeTraveled = this.speed;
		while (distanceToBeTraveled > 0 && (this.nodesPosition.size() - 1 > this.reachedNode)) {
			Position nextPos = this.nodesPosition.get(this.reachedNode + 1);

			// checks if the distance to the next node is higher than the speed x tick to
			// avoid overshooting the objective
			if (Position.findDistance(this.actualPosition, nextPos) > this.speed) {
				double angle = Math.atan2(nextPos.getY() - this.actualPosition.getY(),
						nextPos.getX() - this.actualPosition.getX());
				double newX = this.actualPosition.getX() + this.speed * Math.cos(angle);
				double newY = this.actualPosition.getY() + this.speed * Math.sin(angle);

				this.actualPosition.setPosition(newX, newY);
				distanceToBeTraveled = 0;
			} else {
				distanceToBeTraveled -= Position.findDistance(this.actualPosition, nextPos);
				this.actualPosition.setPosition(nextPos);
				this.reachedNode++;
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void dealDamage(final double damage) {
		this.health -= damage;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isDead() {
		return this.health <= 0;
	}

	/** {@inheritDoc} */
	@Override
	public double getHealth() {
		return this.health;
	}

	/** {@inheritDoc} */
	@Override
	public int getMoney() {
		return this.money;
	}

	/** {@inheritDoc} */
	@Override
	public int getDamage() {
		return this.damage;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPathCompleted() {
		return this.reachedNode + 1 >= this.nodesPosition.size();
	}

	@Override
	public String getEntityName() {
		return this.enemyName;
	}

}
