package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.tas.view.MenuView;

public class MainMenuListener implements ActionListener{
	
	private MenuView theView;
	private int currentComand = 0;
	private boolean update = false;
	
	public MainMenuListener(MenuView theViewIn) {
		this.theView = theViewIn;
	}
	
	public int getCommand() {
		return this.currentComand;
	}
	
	public boolean checkUpdate() {
		return this.update;
	}
	
	public void resetUpdate() {
		this.update = false;
	}
	
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
