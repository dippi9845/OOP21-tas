package main.java.tas.model.tower;

import main.java.tas.model.Entity;
import main.java.tas.utils.Position;
import java.awt.Dimension;

public interface Tower extends Entity, Runnable {
	
	@Override
	default Dimension getBodyDimension() {
		return new Dimension(100, 100);
	}
	
	@Override
	default Position getPosition() {
		return this.getPos();
	}
	
	@Override
	default void run() {
		try {
			this.compute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void compute() throws InterruptedException;
	
	public int getDamage();
	
	public int getRadius();
	
	public int getCost();
	
	public int getDelay();

	public Position getPos();
}
