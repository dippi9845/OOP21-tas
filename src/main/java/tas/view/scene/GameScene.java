package main.java.tas.view.scene;

import java.util.List;

import main.java.tas.view.GameView;
import main.java.tas.view.InventoryView;

/**
 * Interface for a game scene.
 */
public interface GameScene extends GenericScene {
	
	/**
	 * Passes to the inventory view what buttons must be disabled.
	 * @param names the button names
	 */
	void disableButtons(List <String> names);
 

	/**
	 * @return the game view
	 */
	GameView getGameView();
	
	/**
	 * @return the inventory view
	 */
	InventoryView getInventoryView();

}
