package main.java.tas.view;

import javax.swing.JPanel;

import main.java.tas.controller.SceneMouseObserver;

public interface ViewMouse {
	
	public JPanel getPanel();

	public void setMouseObserver(SceneMouseObserver observer);
}
