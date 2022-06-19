package main.java.tas.controller.observer;

import java.awt.event.MouseListener;

import main.java.tas.controller.SceneController;

/**
 * 
 * Interface for a mouse scene observer. Extends {@link SceneController}.
 *
 */
public interface SceneMouseObserver {

	/**
	 * @return the mouse listener
	 */
	public MouseListener getMouseListener();
}
