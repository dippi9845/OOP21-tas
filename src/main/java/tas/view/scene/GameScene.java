package main.java.tas.view.scene;

import main.java.tas.view.GameView;
import main.java.tas.view.InventoryView;

/**
 * Interface for a game scene.
 */
public interface GameScene extends GenericScene {
 


	/**
	 * @return the game view
	 */
	GameView getGameView();
	
	/**
	 * @return the inventory view
	 */
	InventoryView getInventoryView();

}
