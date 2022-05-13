package main.java.tas.controller;

import java.util.List;

import main.java.tas.model.enemies.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.model.tower.Towers;
import java.util.LinkedList;

public class TowerLogicImpl implements TowerLogic {
	private final List<Tower> builtTowers = new LinkedList<Tower>();

	public TowerLogicImpl(final List<Enemy> enemyList) {
		Towers.ENEMYLIST = enemyList;
	}
	
	@Override
	public void buildTower(final Tower t) {
		this.builtTowers.add(t);
	}

}
