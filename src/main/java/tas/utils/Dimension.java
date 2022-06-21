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
	
}
