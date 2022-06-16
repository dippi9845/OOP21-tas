package main.java.tas.view;

import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;

/**
 * Interface for a view which has buttons.
 */
public interface ViewAction {
	
	/**
	 * @return the panel
	 */
	public JPanel getPanel();

	/**
	 * Sets an observer for the view.
	 * 
	 * @param observer the observer
	 */
	public void setActionObserver(SceneActionObserver observer);
}
