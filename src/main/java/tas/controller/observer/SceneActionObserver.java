package main.java.tas.controller.observer;

import java.awt.event.ActionListener;

import main.java.tas.controller.SceneController;

/**
 * 
 * Interface for an action scene observer. Extends {@link SceneController}.
 *
 */
public interface SceneActionObserver {

	/**
	 * @return the action listener
	 */
	public ActionListener getActionListener();

}
