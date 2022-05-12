package main.java.tas.model.tower;

import main.java.tas.model.Entity;
import main.java.tas.utils.Position;
import java.awt.Dimension;

public interface Tower extends Entity {
	
	@Override
	default Dimension getBodyDimension() {
		return new Dimension(100, 100);
	}
	
	@Override
	default Position getPosition() {
		return this.getPos();
	}
	
	public void compute();
	
	public int getDamage();
	
	public int getRadius();
	
	public int getCost();
	
	public int getDelay();

	public Position getPos();
}
