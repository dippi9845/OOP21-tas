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
	
	@Override
	default String getImageName() {
		return this.getTowerImageName();
	}
	
	public void compute();
	
	public int getDamage();
	
	public int getRadius();
	
	public int getCost();
	
	public int getDelay();
	
	public String getTowerImageName();

	public Position getPos();
}
