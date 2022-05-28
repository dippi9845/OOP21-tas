package main.java.tas.model.tower;

import main.java.tas.model.Entity;
import main.java.tas.utils.Position;
import java.awt.Dimension;

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
	 * Method used by thread, to run the tower in concurrency
	 */
	default void run() {
		try {
			this.compute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method implements the behavior of the tower, at every wake up of the Thread
	 * @throws InterruptedException
	 */
	public void compute() throws InterruptedException;
	
	/**
	 * @return Returns the damage of the tower
	 */
	public int getDamage();
	
	/**
	 * @return Returns the radius of the tower
	 */
	public int getRadius();
	
	/**
	 * @return Returns the cost of the tower
	 */
	public int getCost();
	
	/**
	 * @return Returns the delay of the tower
	 */
	public int getDelay();
	
	/**
	 * @return Returns the name of the image linked to the tower
	 */
	public String getTowerImageName();
	
	/**
	 * @return Returns the position of the tower
	 */
	public Position getPos();
}
