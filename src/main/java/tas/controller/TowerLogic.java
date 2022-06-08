package main.java.tas.controller;

import java.util.function.Consumer;
import main.java.tas.model.Entity;
import main.java.tas.model.tower.Builder;
import main.java.tas.model.tower.factory.FactoryList;
import main.java.tas.utils.Position;
/**
 * An interface that manage all the built towers
 */
public interface TowerLogic {
	
	/**
	 * Build a tower in that position
	 * @param tower enumeration with the specific value of the tower
	 * @param pos position of the tower
	 * @return true if the tower is successfully build
	 */
	public boolean placeTower(final FactoryList tower, final Position pos);
	
	/**
	 * Build the tower with specific presets
	 * @param preset Build with all parameters set
	 * @return true if the tower is successfully build 
	 */
	public boolean placeTower(final Builder preset);
	
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
