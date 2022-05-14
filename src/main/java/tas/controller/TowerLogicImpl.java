package main.java.tas.controller;

import java.util.List;
import java.util.function.Consumer;

import main.java.tas.model.Entity;
import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;
import java.util.LinkedList;

public class TowerLogicImpl implements TowerLogic {
	private final List<Thread> builtTowers = new LinkedList<Thread>();
	private final Consumer<Entity> addToPanel;
	
	public TowerLogicImpl(final List<Enemy> enemyList, final Consumer<Entity> addToPanel) {
		Towers.ENEMYLIST = enemyList;
		this.addToPanel = addToPanel;
	}
	
	@Override
	public void buildTower(final Tower t) {
		this.builtTowers.add(new Thread(t));
		this.builtTowers.get(this.builtTowers.size() - 1).run();
		this.addToPanel.accept(t);
	}
	
	// TODO abbelire
	@Override
	public void closeAll() {
		this.builtTowers.stream().forEach(x->{
			try {
				x.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}
