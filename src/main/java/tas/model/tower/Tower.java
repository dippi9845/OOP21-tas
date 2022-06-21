package main.java.tas.model.tower;

import main.java.tas.model.Entity;
import main.java.tas.model.tower.factory.DefaultTowersUtils;
import main.java.tas.utils.Position;
import main.java.tas.utils.Dimension;

/**
 * An Interface that model a generic Tower, that extends Entity
 */
public interface Tower extends Entity{

	/** {@inheritDoc} */
	@Override
	default Dimension getBodyDimension() {
		return DefaultTowersUtils.DEFAULTDIMENSION;
	}

	/** {@inheritDoc} */
	@Override
	default Position getPosition() {
		return this.getPos();
	}

	/** {@inheritDoc} */
	@Override

	default String getEntityName() {
		return this.getTowerName();
	}
	
	/**
	 * This method implements the behavior of the tower at every second, that change depending from the implementation.
	 */
	public void compute();

	/**
	 * Return the damage of the tower
	 * @return the damage of the tower
	 */
	public int getDamage();

	/**
	 * Return the radius of the tower
	 * @return the radius of the tower
	 */
	public int getRadius();

	/**
	 * Return the cost of the tower
	 * @return the cost of the tower
	 */
	public int getCost();

	/**
	 * Return the delay of the tower
	 * @return the delay of the tower
	 */
	public int getDelay();

	/**
	 * Return the name of the tower
	 * @return the name of the tower
	 */
	public String getTowerName();

	/**
	 * Return the position of the tower
	 * @return the position of the tower
	 */
	public Position getPos();
}
