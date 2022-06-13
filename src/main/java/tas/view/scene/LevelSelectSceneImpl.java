package main.java.tas.view.scene;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.java.tas.model.MenuModel;
import main.java.tas.view.LevelSelectView;
import main.java.tas.view.SceneActionObserver;

/**
 * Class that builds the level select menu scene.
 * Class that implements {@link ActionScene}.
 */
public class LevelSelectSceneImpl implements ActionScene {
	
	private JPanel rootPanel;
	private LevelSelectView View;
	
	/**
     * Constructor that set up the select level scene
     * @param rootPanelIn is the {@link JPanel} that will contain the scene
     * @param menuModel the menu model
     */
	public LevelSelectSceneImpl(JPanel rootPanelIn, MenuModel menuModel) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        
        this.View = new LevelSelectView(menuModel);
        
        this.rootPanel.add(this.View.getPanel(), BorderLayout.CENTER);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setActionObserver(SceneActionObserver listener) {
		this.View.setActionObserver(listener);
	}

	/**
	 * 
	 * @return the level select view.
	 */
	public LevelSelectView getView() {
		return this.View;
	}
}
	

