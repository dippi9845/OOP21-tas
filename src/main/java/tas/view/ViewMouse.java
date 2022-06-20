package main.java.tas.view;

import main.java.tas.controller.observer.SceneMouseObserver;

/**
 * Interface for a view which checks if the mouse clicks on it.
 */
public interface ViewMouse extends GenericView {

	/**
	 * Sets an observer for the view.
	 * 
	 * @param observer the observer
	 */
	public void setMouseObserver(SceneMouseObserver observer);
}
