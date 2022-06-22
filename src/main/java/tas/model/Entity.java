package main.java.tas.model;

import main.java.tas.utils.Size;

import main.java.tas.utils.Position;

/**
 * An interface for any graphic entity in the game.
 */
public interface Entity {

	/**
	 * Return the position of the entity
	 * @return the position of the entity
	 */
	Position getPosition();

	/**
	 * Return the dimension of the entity
	 * @return the dimension of the entity
	 */
	Size getBodyDimension();

	/**
	 * Return the name of the entity
	 * @return the name of the entity
	 */
	String getEntityName();

}
