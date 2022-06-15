package main.java.tas.controller;

public class GenericListener {

	private boolean update = false;
	
	/**
	 * 
	 * @return update
	 */
	public boolean checkUpdate() {
		return this.update;
	}
	
	/**
	 * sets the update to false.
	 */
	public void resetUpdate() {
		this.update = false;
	}
	
	public void setUpdate() {
		this.update = true;
	}
}
