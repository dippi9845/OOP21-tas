package main.java.tas.controller.tower;

import java.util.function.Consumer;
import main.java.tas.model.Entity;
import main.java.tas.model.tower.TowerBuilder;
import main.java.tas.model.tower.factory.DefaultTowers;
import main.java.tas.utils.Position;

/**
 * An interface that manage all the built towers
 */
public interface TowerLogic {

	/**
	 * Build a tower in that position
	 * 
	 * @param tower enumeration with the specific value of the tower
	 * @param pos   position of the tower
	 * @return true if the tower is successfully build
	 */
	public boolean placeTower(final DefaultTowers tower, final Position pos);

	/**
	 * Build the tower with specific presets
	 * 
	 * @param preset Build with all parameters set
	 * @return true if the tower is successfully build
	 */
	public boolean placeTower(final TowerBuilder preset);

	/**
	 * Terminate all Tower placed function and wait for all threads to join
	 */
	public void closeAll();

	/**
	 * Checks if a position passed is too close to another tower builded
	 * @param pos position to be checked
	 * @return true if position in not closer to any tower, false otherwise
	 */
	public boolean thereIsTowerNear(final Position pos);
	
	/**
	 * draw all the towers
	 * 
	 * @param draw consumer of Entity that draw every tower built
	 */
	public void drawTowers(final Consumer<Entity> draw);
}
