package main.java.tas.controller.lister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

/**
 * Class that implements {@link ActionListener} used as a listener for the
 *  level select menu.
 *  Class that extends {@link GenericListener}
 */
public class LevelSelectListener extends GenericListener implements ActionListener{
	
	private int currentComand = 0;
	
	/**
	 * @return the currentCommand
	 */
	public int getCommand() {
		return this.currentComand;
	}
	
	/** {@inheritDoc} */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.currentComand = Integer.parseInt(((AbstractButton) e.getSource()).getText());
		setUpdate();
	}
}
