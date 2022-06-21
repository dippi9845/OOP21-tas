package main.java.tas.controller.listener;

/**
 * A basic class for other listeners to extend
 */
public class GenericListener {

	private boolean update = false;

	/**
	 * @return update
	 */
	public boolean checkUpdate() {
		return this.update;
	}

	/**
	 * Sets the update to false.
	 */
	public void resetUpdate() {
		this.update = false;
	}

	/**
	 * Sets the update to true
	 */
	public void setUpdate() {
		this.update = true;
	}
}
