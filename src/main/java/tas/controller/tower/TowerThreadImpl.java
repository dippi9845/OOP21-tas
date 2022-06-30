package main.java.tas.controller.tower;

import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;

/**
 * A class that model a thread that contains a tower, and it run concurrently to
 * the main thread
 *
 */
public class TowerThreadImpl implements TowerThread {
	private final Tower tower;
	private boolean stopTh;

	/**
	 * Constructor, with only one parameter, a tower that we want to run in our
	 * thread
	 * 
	 * @param t tower that we want to run in our thread
	 */
	protected TowerThreadImpl(final Tower t) {
		this.tower = t;
		this.stopTh = false;
	}

	/** {@inheritDoc} */
	@Override
	public void run() {
		while (!this.isStop()) {
			this.tower.compute();
		}

	}
	
	/**
	 * This function sleeps the current thread, depending from the defined field
	 * delay {@link: AbstractBasicTower#delay}
	 */
	protected void sleep() {
		try {
			Thread.sleep(this.getDelay());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean isStop() {
		return this.stopTh;
	}

	/** {@inheritDoc} */
	@Override
	public void stop() {
		this.stopTh = true;
	}

	/** {@inheritDoc} */
	@Override
	public void compute() {
		this.tower.compute();
		this.sleep();
	}

	/** {@inheritDoc} */
	@Override
	public int getDamage() {
		return this.tower.getDamage();
	}

	/** {@inheritDoc} */
	@Override
	public int getRadius() {
		return this.tower.getRadius();
	}

	/** {@inheritDoc} */
	@Override
	public int getCost() {
		return this.tower.getCost();
	}

	/** {@inheritDoc} */
	@Override
	public int getDelay() {
		return this.tower.getDelay();
	}

	/** {@inheritDoc} */
	@Override
	public String getTowerName() {
		return this.tower.getTowerName();
	}

	/** {@inheritDoc} */
	@Override
	public Position getPos() {
		return this.tower.getPos();
	}

}
