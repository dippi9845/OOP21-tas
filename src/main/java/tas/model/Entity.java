package main.java.tas.model;

import java.awt.Dimension;

import main.java.tas.utils.Position;

/**
 * An interface for any graphic entity in the game.
 */
public interface Entity {

	/**
	 * @return the position of the entity
	 */
	Position getPosition();

	/**
	 * @return the dimension of the entity
	 */
	Dimension getBodyDimension();

	/**
	 * @return the name of the entity
	 */
	String getEntityName();

}
