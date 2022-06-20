package main.java.tas.controller.lister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.tas.view.ViewAction;

/**
 * Class that implements {@link ActionListener} used as a listener for the
 *  level select menu.
 *  Class that extends {@link GenericListener}
 */
public class LevelSelectListener extends GenericListener implements ActionListener{
	
	private int currentComand = 0;
	private ViewAction view;
	
	public LevelSelectListener(ViewAction theView) {
		this.view = theView;
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
		for(JButton button : this.view.getButtons()) {
			counter++;
			if(e.getSource() == button) {
				this.currentComand = counter;
			}
		}
		setUpdate();
	}
}
