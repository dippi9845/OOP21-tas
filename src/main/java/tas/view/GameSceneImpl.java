package main.java.tas.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import main.java.tas.controller.SceneController;

public class GameSceneImpl implements GameScene {
	
    private final JPanel rootPanel;
    private final GameView gameView;
    private final ViewComponent inventoryView;
    
    public GameSceneImpl(JPanel root) {
        this.rootPanel = root;
        this.rootPanel.setLayout(new BorderLayout());
        
        this.gameView = new GameView();
        this.inventoryView = new InventoryView();
        
        this.rootPanel.add(this.gameView.getPanel(), BorderLayout.CENTER);
        this.rootPanel.add(this.inventoryView.getPanel(), BorderLayout.EAST);
        
	}

    public void setObserver(SceneController controller) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public GameView getGameView() {
        return this.gameView;
    }

}
