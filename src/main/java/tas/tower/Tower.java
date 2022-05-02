package main.java.tas.tower;

import main.java.tas.utils.Position;

public interface Tower {
	
	public void compute();
	
	public int getDamage();
	
	public int getRadius();
	
	public int getCost();
	
	public int getDelay();

	public Position getPos();
}
