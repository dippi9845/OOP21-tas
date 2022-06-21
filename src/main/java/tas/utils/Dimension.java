package main.java.tas.utils;

public class Dimension {

	private double width;
	private double height;

	public Dimension(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Returns the diagonal of the rectangle described by Dimension
	 * 
	 * @param d the dimension of the rectangle
	 * @return the diagonal described by the rectangle described by Dimension
	 */
	public double getDiagonal() {
		return Math.hypot(this.width, this.height) / 2;
	}

}
