package main.java.tas.controller.lister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

/**
 * Class that implements {@link ActionListener} used as a listener for the
 *  full levels menu.
 * Class extends {@link GenericListener}.
 */
public class FullLevelsListener extends GenericListener implements ActionListener{
	
	private int currentComand = 0;
	
	/**
	 * 
	 * @return the currentCommand
	 */
	public int getCommand() {
		return this.currentComand;
	}
	
	/** {@inheritDoc} */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((AbstractButton) e.getSource()).getText() == "BACK") {
			this.currentComand = 1;
		}
		if(((AbstractButton) e.getSource()).getText() == "DELETE USER LEVELS") {
			this.currentComand = 2;
		}		
		setUpdate();
	}
}
