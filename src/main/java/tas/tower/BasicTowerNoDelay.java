package main.java.tas.tower;

import main.java.tas.utils.Position;

public class BasicTowerNoDelay extends BasicTower {

	BasicTowerNoDelay(Position pos, int damage, int radius) {
		super(pos, damage, radius, 100); // it seem like have not a delay, but it actually has it
	}

}
