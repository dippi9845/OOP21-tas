package main.java.tas.view;

import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;

public interface ViewAction {
	
	public JPanel getPanel();

	public void setActionObserver(SceneActionObserver observer);
}
