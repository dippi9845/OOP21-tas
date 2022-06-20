package main.java.tas.controller.lister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

/**
 * Class that implements {@link ActionListener} used as a listener for the
 *  full levels menu.
 * Class extends {@link GenericListener}.
 */
public class FullLevelsListener extends GenericListener implements ActionListener{
	
	private int currentComand = 0;
	private List <JButton> buttonList;
	
	public FullLevelsListener(List <JButton> buttons) {
		this.buttonList = buttons;
	}
	
	/**
	 * @return the currentCommand
	 */
	public int getCommand() {
		return this.currentComand;
	}
	
	/** {@inheritDoc} */
	@Override
	public void actionPerformed(ActionEvent e) {
		int counter = 0;
		for(JButton button : this.buttonList) {
			counter++;
			if(e.getSource() == button) {
				this.currentComand = counter;
			}	
		}
		setUpdate();
	}
}
