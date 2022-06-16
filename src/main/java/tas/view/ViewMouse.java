package main.java.tas.view;

import javax.swing.JPanel;

import main.java.tas.controller.SceneMouseObserver;

/**
 * Interface for a view which checks if the mouse clicks on it.
 */
public interface ViewMouse {
	
	/**
	 * @return the panel
	 */
	public JPanel getPanel();

	/**
	 * Sets an observer for the view.
	 * 
	 * @param observer the observer
	 */
	public void setMouseObserver(SceneMouseObserver observer);
}
