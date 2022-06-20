package main.java.tas.model.tower;

import main.java.tas.model.Entity;
import main.java.tas.utils.Position;
import java.awt.Dimension;

/**
 * An Interface that model a generic Tower, that extends Entity, and TowerThread
 */
public interface Tower extends Entity, TowerThread {

	/** {@inheritDoc} */
	@Override
	default Dimension getBodyDimension() {
		return new Dimension(100, 100);
	}

	/** {@inheritDoc} */
	@Override
	default Position getPosition() {
		return this.getPos();
	}

	/** {@inheritDoc} */
	@Override
	default String getImageName() {
		return this.getTowerName();
	}

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
	 * Return the name of the image linked to the tower
	 * @return the name of the image linked to the tower
	 */
	public String getTowerName();

	/**
	 * Return the position of the tower
	 * @return the position of the tower
	 */
	public Position getPos();
}
