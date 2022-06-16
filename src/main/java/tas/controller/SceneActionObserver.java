package main.java.tas.controller;

import java.awt.event.ActionListener;

/**
 * 
 * Interface for an action scene observer.
 * Extends {@link SceneController}.
 *
 */
public interface SceneActionObserver extends SceneController {
	
	/**
	 * @return the action listener
	 */
	public ActionListener getActionListener();
	
}
