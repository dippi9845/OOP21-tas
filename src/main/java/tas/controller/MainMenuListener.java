package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.tas.view.view.MenuView;

/**
 * 
 * Class that implements {@link ActionListener} used as a listener for
 * the main menu scene.
 * Class that extends {@link GenericListener}.
 *
 */
public class MainMenuListener extends GenericListener implements ActionListener{
	
	private MenuView theView;
	private int currentComand = 0;
	
	/**
	 * Constructor that creates the main menu listener.
	 * @param theViewIn the main menu view
	 */
	public MainMenuListener(MenuView theViewIn) {
		this.theView = theViewIn;
	}
	
	/**
	 * @return the current command
	 */
	public int getCommand() {
		return this.currentComand;
	}
	
	/**
	 * sets the current command to the correct value depending on what button has been
	 * pressed. It also sets update to true
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== theView.getNewGameButton()) {
			this.currentComand = 1;
			setUpdate();
		}
		
		else if(e.getSource() == theView.getSettingsButton()) {
			this.currentComand = 2;
			setUpdate();
		}
		
		else if(e.getSource() == theView.getSandboxModeButton()) {
			this.currentComand = 3;
			setUpdate();
		}
		
		else if(e.getSource() == theView.getExitButton()) {
			this.currentComand = 4;
			setUpdate();
		}
	}
}
