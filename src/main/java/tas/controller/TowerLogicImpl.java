package main.java.tas.controller;

import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;
import java.util.LinkedList;

public class TowerLogicImpl implements TowerLogic {
	private final List<Thread> builtTowers = new LinkedList<Thread>();

	public TowerLogicImpl(final List<Enemy> enemyList) {
		Towers.ENEMYLIST = enemyList;
	}
	
	@Override
	public void buildTower(final Tower t) {
		this.builtTowers.add(new Thread(t));
	}
	
	// TODO abbelire
	public void closeAll() throws InterruptedException {
		this.builtTowers.stream().forEach(x->{
			try {
				x.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}
