package main.java.tas.utils;

/**
 * Class that models a dimension.
 */
public class Dimension {

	private double width;
	private double height;

	/**
	 * Constructor that set up a dimension by double values.
	 * @param width of the dimension.
	 * @param height of the dimension.
	 */
	public Dimension(double width, double height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Constructor that set up a dimension by integer values.
	 * @param width of the dimension.
	 * @param height of the dimension.
	 */
	public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the width of the dimension.
	 */
	public double getWidth() {
		return this.width;
	}

	/**
	 * @return the height of the dimension.
	 */
	public double getHeight() {
		return this.height;
	}

}
