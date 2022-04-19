package main.java.tas.tower;

import java.util.Optional;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public class MortarTower extends AbstractMultipleTower {
	
	
	protected MortarTower(Position pos, int damage, int radius, int delay, int maxTarget, int minRange, int attackRange) {
		super(pos, damage, radius, delay, Integer.MAX_VALUE);

	}
	
	private boolean isFirstInRange(final Enemy e) {
		
	}

	@Override
	protected boolean isValidTarget(final Enemy e) {
		
	}

	@Override
	public void compute() {
		
		// TODO sleep
	}


}
