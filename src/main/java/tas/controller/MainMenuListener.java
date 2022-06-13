package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.tas.view.MenuView;

/**
 * 
 * Class that implements {@link ActionListener} used as a listener for
 * the main menu scene.
 *
 */
public class MainMenuListener implements ActionListener{
	
	private MenuView theView;
	private int currentComand = 0;
	private boolean update = false;
	
	/**
	 * Constructor that creates the main menu listener.
	 * @param theViewIn the main menu view
	 */
	public MainMenuListener(MenuView theViewIn) {
		this.theView = theViewIn;
	}
	
	/**
	 * 
	 * @return the current command
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
	 * sets the update to false.
	 */
	public void resetUpdate() {
		this.update = false;
	}
	/**
	 * sets the current command to the correct value depending on what button has been
	 * pressed. It also sets update to true
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== theView.getNewGameButton()) {
			this.currentComand = 1;
			this.update = true;
		}
		
		else if(e.getSource() == theView.getSettingsButton()) {
			this.currentComand = 2;
			this.update = true;
		}
		
		else if(e.getSource() == theView.getSandboxModeButton()) {
			this.currentComand = 3;
			this.update = true;
		}
		
		else if(e.getSource() == theView.getExitButton()) {
			this.currentComand = 4;
			this.update = true;
		}
	}
}
