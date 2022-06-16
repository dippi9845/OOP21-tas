package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that implements {@link ActionListener} used as a listener for a single button.
 * Extends {@link GenericListener}
 *
 */
public class ButtonListener extends GenericListener implements ActionListener{
	
	/**
	 * Sets the update to true if an action is performed.
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setUpdate();
	}
}

