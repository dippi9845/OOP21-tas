package main.java.tas.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.tas.view.ViewAction;

/**
 * 
 * Class that implements {@link ActionListener} used as a listener for the main
 * menu scene. Class that extends {@link GenericListener}.
 *
 */
public class MainMenuListener extends GenericListener implements ActionListener {

	private ViewAction theView;
	private int currentComand = 0;

	/**
	 * Constructor that creates the main menu listener.
	 * 
	 * @param theViewIn the main menu view
	 */
	public MainMenuListener(ViewAction theViewIn) {
		this.theView = theViewIn;
	}

	/**
	 * @return the current command
	 */
	public int getCommand() {
		return this.currentComand;
	}

	/**
	 * sets the current command to the correct value depending on what button has
	 * been pressed. It also sets update to true
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int counter = 0;
		for (JButton button : this.theView.getButtons()) {
			counter++;
			if (e.getSource() == button) {
				this.currentComand = counter;
			}
		}
		setUpdate();
	}
}
