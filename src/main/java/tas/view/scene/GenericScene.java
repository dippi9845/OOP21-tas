package main.java.tas.view.scene;

import main.java.tas.controller.SceneController;
import main.java.tas.view.GenericView;

/**
 * Interface for a generic scene.
 */
public interface GenericScene {
	
	/**
	 * Set up an observer for the scene.
	 * 
	 * @param observer is the observer
	 */
	void setObserver(SceneController observer);
	
	/**
	 * 
	 * @return the view
	 */
	public GenericView getView();
}
