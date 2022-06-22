package main.java.tas.utils;

/**
 * Class that models a position.
 */
public class Position {

	private double x;
	private double y;

	/**
	 * Constructor that set up the position.
	 * 
	 * @param x the first coordinate
	 * @param y the second coordinate
	 */
	public Position(final double x, final double y) {
		setPosition(x, y);
	}

	/**
	 * Allows to overwrite the old position.
	 * 
	 * @param pos the new position
	 */
	public void setPosition(final Position pos) {
		setPosition(pos.getX(), pos.getY());
	}

	/**
	 * Allows to overwrite the old position.
	 * 
	 * @param x the first coordinate
	 * @param y the second coordinate
	 */
	public void setPosition(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the first coordinate
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @return the second coordinate
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Calculate the distance between 2 coordinates.
	 * 
	 * @param pos1 the first position
	 * @param pos2 the second position
	 * @return the distance between the 2 coordinate
	 */
	public static double findDistance(final Position pos1, final Position pos2) {
		return Math.hypot(pos1.getX() - pos2.getX(), pos1.getY() - pos2.getY());
	}

	/**
	 * Converts the values of x and y into the values depending on the window
	 * dimensions.
	 * 
	 * @param dim          the current dimensions of the game board
	 * @param componentDim the current dimensions of the window
	 */
	public void positionConverter(Size dim, Size componentDim) {
		double newX = x * dim.getWidth() / componentDim.getWidth();
		double newY = y * dim.getHeight() / componentDim.getHeight();
		this.x = newX;
		this.y = newY;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return new String(x + " - " + y);
	}

}
