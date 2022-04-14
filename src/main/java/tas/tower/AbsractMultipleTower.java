package main.java.tas.tower;

import java.util.LinkedList;
import java.util.List;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.utils.Position;

public abstract class AbsractMultipleTower extends AbstractBasicTower implements Tower {
	final List<Enemy> enemyList = new LinkedList<>();
	final int maxEnemy;
	
	private boolean isFull() {
		return this.enemyList.size() < this.maxEnemy;
	}
	
	protected AbsractMultipleTower(Position pos, int damage, int radius, int delay, final int maxTarget) {
		super(pos, damage, radius, delay);
		this.maxEnemy = maxTarget;
	}

	@Override
	protected void attack() {
		
	}

	@Override
	protected void setTarget(Enemy e) {
		
	}

	@Override
	public void compute() {
		

	}

}
