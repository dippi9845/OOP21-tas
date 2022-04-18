package main.java.tas.tower;

import main.java.tas.utils.Position;

public abstract class AbstractGasTower extends AbsractMultipleTower {

	protected AbstractGasTower(Position pos, int damage, int radius, int delay) {
		super(pos, damage, radius, delay, Integer.MAX_VALUE);
	}

}
