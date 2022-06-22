package main.java.tas.controller.tower;

import java.util.List;
import java.util.function.Consumer;

import main.java.tas.controller.tower.builder.TowerBuilder;
import main.java.tas.controller.tower.factory.DefaultTowers;
import main.java.tas.model.Entity;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.Position;
import main.java.tas.utils.Dimension;

/**
 * An interface that manage all the built towers
 */
public interface TowerController {

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
	 * All tower placed will be removed, all Thread created will be deleted
	 */
	public void closeAll();

	/**
	 * Checks if the position, and dimension passed is too close to another tower
	 * builded
	 * 
	 * @param pos Position of the tower to build
	 * @param dim Dimension of the tower to build
	 * @return true if is closer to minimum one tower, false otherwise
	 */
	public boolean thereIsTowerNear(final Position pos, final Dimension dim);

	/**
	 * draw all the towers
	 * 
	 * @param draw consumer of Entity that draw every tower built
	 */
	public void drawTowers(final Consumer<Entity> draw);

	/**
	 * Return a {@link java.util.List} containing all built towers, by the method
	 * {@link TowerController#placeTower(DefaultTowers, Position)} or the method
	 * {@link TowerController#placeTower(TowerBuilder)}
	 * 
	 * @return a {@link java.util.List} containing all built towers
	 */
	public List<Tower> getBuildTowers();

	/**
	 * Return a {@link java.util.List} containing all thread, created when was
	 * called, the method
	 * {@link TowerController#placeTower(DefaultTowers, Position)} or the method
	 * {@link TowerController#placeTower(TowerBuilder)}
	 * 
	 * @return a {@link java.util.List} containing all thread created
	 */
	public List<Thread> getBuildThread();
}
