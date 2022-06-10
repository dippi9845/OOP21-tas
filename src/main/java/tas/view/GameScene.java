package main.java.tas.view;

import main.java.tas.controller.SceneController;

/**
 * Interface for a game scene.
 */
public interface GameScene {
 
	/**
	 * Set up an observer for the scene.
	 * 
	 * @param observer is the observer
	 */
	void setObserver(SceneController observer);

	/**
	 * @return the game view
	 */
	GameView getGameView();

}
