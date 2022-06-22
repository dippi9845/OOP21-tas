package main.java.tas.model.tower;

import java.util.List;

import main.java.tas.model.enemy.Enemy;

public interface TowerBuilder {
	public int getRange();
	
	public TowerBuilder setEnemylist(final List<Enemy> enemyList);
	
}
