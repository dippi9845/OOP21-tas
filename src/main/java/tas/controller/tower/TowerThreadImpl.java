package main.java.tas.controller.tower;

import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

public class TowerThreadImpl implements TowerThread{
	private final Tower tower;
	private boolean stopTh;
	
	protected TowerThreadImpl(final Tower t) {
		this.tower = t;
		this.stopTh = false;
	}
	
	@Override
	public void run() {
		while (!this.isStop()) {
			this.tower.compute();
		}

	}

	@Override
	public boolean isStop() {
		return this.stopTh;
	}

	@Override
	public void stop() {
		this.stopTh = true;
	}

	@Override
	public void compute() {
		this.tower.compute();
	}

	@Override
	public int getDamage() {
		return this.tower.getDamage();
	}

	@Override
	public int getRadius() {
		return this.tower.getRadius();
	}

	@Override
	public int getCost() {
		return this.tower.getCost();
	}

	@Override
	public int getDelay() {
		return this.tower.getDelay();
	}

	@Override
	public String getTowerName() {
		return this.tower.getTowerName();
	}

	@Override
	public Position getPos() {
		return this.tower.getPos();
	}

}
