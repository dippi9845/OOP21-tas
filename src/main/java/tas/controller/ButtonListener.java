package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that implements {@link ActionListener} used as a listener for a single button.
 * 
 *
 */
public class ButtonListener implements ActionListener{
	
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
	/**
	 * Sets the update to true if an action is performed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.update = true;
	}
}

