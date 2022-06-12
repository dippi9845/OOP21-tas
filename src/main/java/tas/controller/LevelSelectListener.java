package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

/**
 * Class that implements {@link ActionListener} used as a listener for the
 *  level select menu.
 * 
 *
 */
public class LevelSelectListener implements ActionListener{
	
	private int currentComand = 0;
	private boolean update = false;
	
	/**
	 * 
	 * @return the currentCommand
	 */
	public int getCommand() {
		return this.currentComand;
	}
	
	/**
	 * 
	 * @return update
	 */
	public boolean checkUpdate() {
		return this.update;
	}
	
	/**
	 * It sets the update to false
	 */
	public void resetUpdate() {
		this.update = false;
	}
	
	/** {@inheritDoc} */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.currentComand = Integer.parseInt(((AbstractButton) e.getSource()).getText());
		this.update = true;
	}
}
