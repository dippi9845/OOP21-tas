package main.java.tas.controller;

import java.util.function.Consumer;
import main.java.tas.model.Entity;
import main.java.tas.model.tower.Tower;

public interface TowerLogic {
	
	public boolean buildTower(final Tower t);
	
	public void closeAll();
	
	public void drawTowers(final Consumer<Entity> draw);
}
