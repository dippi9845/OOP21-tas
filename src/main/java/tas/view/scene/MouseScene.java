package main.java.tas.view.scene;

import main.java.tas.view.SceneMouseObserver;

public interface MouseScene {

	/**
	 * Set up an observer for the scene.
	 * 
	 * @param observer is the observer
	 */
	void setMouseObserver(SceneMouseObserver observer);
}
