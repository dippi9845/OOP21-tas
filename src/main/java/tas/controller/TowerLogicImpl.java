package main.java.tas.controller;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import main.java.tas.model.Entity;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;
import java.util.LinkedList;

/**
 * A class that has the objective to keep controlled all the built towers, and create them
 */
public class TowerLogicImpl implements TowerLogic {
	private final List<Tower> builtTowers = new LinkedList<Tower>();
	private final List<Thread> towerThreads = new LinkedList<Thread>();
	private final Consumer<Entity> addToPanel;
	private final Predicate<Integer> spendMoney;
	
	/**
	 * Constructor
	 * @param enemyList list that contains all the enemies alive
	 * @param addToPanel function that add towers to the panel
	 * @param spendMoney
	 */
	public TowerLogicImpl(final List<Enemy> enemyList, final Consumer<Entity> addToPanel, final Predicate<Integer> spendMoney) {
		Towers.initEnemyList(enemyList);
		this.addToPanel = addToPanel;
		this.spendMoney = spendMoney;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean buildTower(final Tower t) {
		if (this.spendMoney.test(t.getCost())) {
			final Thread th = new Thread(t);
			th.start();
			this.towerThreads.add(th);
			
			this.builtTowers.add(t);
			this.addToPanel.accept(t);
			return true;
		}
		else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public void closeAll() {
		this.towerThreads.stream().forEach(x->{
			try {
				x.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	/** {@inheritDoc} */
	@Override
	public void drawTowers(final Consumer<Entity> draw) {
		this.builtTowers.forEach(draw::accept);
	}

}
