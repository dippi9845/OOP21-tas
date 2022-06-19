package main.java.tas.model.tower;

import main.java.tas.model.Entity;
import main.java.tas.utils.Position;
import java.awt.Dimension;

/**
 * An Interface that model a generic Tower, that extends Entity and Runnable
 * 
 * Extends Runnable: so every tower can run concurrently
 */
public interface Tower extends Entity, Runnable {

	/**
	 * {@inheritDoc}
	 */
	@Override
	default Dimension getBodyDimension() {
		return new Dimension(100, 100);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	default Position getPosition() {
		return this.getPos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	default String getImageName() {
		return this.getTowerImageName();
	}

	/**
	 * Return true if is time to stop for the thread, false if the current thread can go on
	 * 
	 * @return true if is time to stop for the thread, false if the current thread can go on
	 */
	public boolean isStop();

	/**
	 * This function is called when is necessary to stop the run method {@link Tower#run()},
	 * after a call to this method {@link Tower#isStop()} must return true
	 */
	public void stop();

	/**
	 * Method used by thread, to run the tower in concurrency
	 */
	default void run() {
		while (!this.isStop()) {
			this.compute();
		}
	}

	/**
	 * This method implements the behavior of the tower, that change depending from the implementation,
	 * is used in the run method {@link Tower#run()}.
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
	 * Return the name of the image linked to the tower
	 * @return the name of the image linked to the tower
	 */
	public String getTowerImageName();

	/**
	 * Return the position of the tower
	 * @return the position of the tower
	 */
	public Position getPos();
}
