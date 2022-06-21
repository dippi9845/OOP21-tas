package main.java.tas.view;

import java.util.List;

import javax.swing.JButton;

import main.java.tas.controller.observer.SceneActionObserver;

/**
 * Interface for a view which has buttons.
 */
public interface ViewAction extends GenericView {

	/**
	 * @return the buttons of the view
	 */
	public List<JButton> getButtons();

	/**
	 * Sets an observer for the view.
	 * 
	 * @param observer the observer
	 */
	public void setActionObserver(SceneActionObserver observer);
}
