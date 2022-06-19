package main.java.tas.controller;

import java.awt.event.MouseListener;

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
