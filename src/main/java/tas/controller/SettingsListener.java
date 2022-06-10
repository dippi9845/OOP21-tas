package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsListener implements ActionListener{
	
	private boolean update = false;
	
	
	public boolean checkUpdate() {
		return this.update;
	}
	
	public void resetUpdate() {
		this.update = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.update = true;
	}
}

