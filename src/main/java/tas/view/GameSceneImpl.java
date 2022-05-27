package main.java.tas.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import main.java.tas.controller.SceneController;

/**
 * Class that implements a {@link GameScene}
 */
public class GameSceneImpl implements GameScene {
	
    private final JPanel rootPanel;
    private final GameView gameView;
    private final ViewComponent inventoryView;
    
    /**
     * Constructor that set up the game scene
     * @param root is the {@link JPanel} that will contain the scene
     */
    public GameSceneImpl(JPanel root) {
        this.rootPanel = root;
        this.rootPanel.setLayout(new BorderLayout());
        
        this.gameView = new GameView();
        this.inventoryView = new InventoryView();
        
        this.rootPanel.add(this.gameView.getPanel(), BorderLayout.CENTER);
        this.rootPanel.add(this.inventoryView.getPanel(), BorderLayout.EAST);
        
	}

    /** {@inheritDoc} */
    @Override
    public void setObserver(SceneController observer) {
        // TODO Auto-generated method stub
        
    }

    public GameView getGameView() {
        return this.gameView;
    }

}
