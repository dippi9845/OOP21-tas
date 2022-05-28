package main.java.tas.controller;

import java.util.function.Consumer;
import main.java.tas.model.Entity;
import main.java.tas.model.tower.Tower;
/**
 * An interface that manage all the built towers
 */
public interface TowerLogic {
	
	/**
	 * Actual build the tower
	 * @param t tower
	 * @return true if succeed, false was not possible to build that tower
	 */
	public boolean buildTower(final Tower t);
	
	/**
	 * Wait for all threads to join
	 */
	public void closeAll();
	
	/**
	 * draw all the towers
	 * @param draw consumer of Entity that draw every tower built
	 */
	public void drawTowers(final Consumer<Entity> draw);
}
