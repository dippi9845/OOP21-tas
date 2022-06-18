package main.java.tas.view.scene;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;
import main.java.tas.controller.SceneController;
import main.java.tas.controller.SceneMouseObserver;
import main.java.tas.view.GameView;
import main.java.tas.view.InventoryView;

/**
 * Class that implements a {@link GameScene}.
 */
public class GameSceneImpl implements GameScene {

	private final JPanel rootPanel;
	private final GameView gameView;
	private final InventoryView inventoryView;

	/**
	 * Constructor that set up the game scene.
	 * 
	 * @param root is the {@link JPanel} that will contain the scene
	 * @param objects list of the tower names
	 * @param <T> the class of objects
	 */
	public <T extends Enum <T>> GameSceneImpl(JPanel root, Class <T> objects) {
		this.rootPanel = root;
		this.rootPanel.setLayout(new BorderLayout());

		this.gameView = new GameView();
		this.inventoryView = new InventoryView(objects);

		this.rootPanel.add(this.gameView.getPanel(), BorderLayout.CENTER);
		this.rootPanel.add(this.inventoryView.getPanel(), BorderLayout.EAST);

	}

	/** {@inheritDoc} */
	@Override
	public void setObserver(SceneController observer) {
		this.inventoryView.setActionObserver((SceneActionObserver) observer);
		this.gameView.setMouseObserver((SceneMouseObserver) observer);
	}

	/** {@inheritDoc} */
	@Override
	public GameView getGameView() {
		return this.gameView;
	}
	
	/** {@inheritDoc} */
	@Override
	public InventoryView getInventoryView() {
		return this.inventoryView;
	}

	@Override
	public void disableButtons(List<String> names) {
		this.inventoryView.disableButtons(names);
	}
}
