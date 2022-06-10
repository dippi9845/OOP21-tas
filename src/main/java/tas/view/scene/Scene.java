package main.java.tas.view.scene;

import main.java.tas.controller.SceneController;

public interface Scene {
	/**
	 * Set up an observer for the scene.
	 * 
	 * @param observer is the observer
	 */
	void setObserver(SceneController observer);
}
