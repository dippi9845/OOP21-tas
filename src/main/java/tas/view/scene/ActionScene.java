package main.java.tas.view.scene;

import main.java.tas.view.SceneActionObserver;

public interface ActionScene extends GenericScene {
	/**
	 * Set up an observer for the scene.
	 * 
	 * @param observer is the observer
	 */
	void setActionObserver(SceneActionObserver observer);
}
