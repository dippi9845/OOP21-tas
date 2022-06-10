package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;


public class LevelSelectListener implements ActionListener{
	
	private int currentComand = 0;
	private boolean update = false;
	
	
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
		this.currentComand = Integer.parseInt(((AbstractButton) e.getSource()).getText());
		this.update = true;
	}

}
