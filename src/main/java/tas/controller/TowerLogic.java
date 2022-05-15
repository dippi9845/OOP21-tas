package main.java.tas.controller;

import main.java.tas.model.tower.Tower;

public interface TowerLogic {
	
	// add tower t to the list of current towers
	public boolean buildTower(final Tower t);
	
	public void closeAll();
}
