package main.java.tas.view.scene;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;
import main.java.tas.controller.SceneController;
import main.java.tas.view.MenuView;

/**
 * 
 * Class that implements {@link GenericScene}.
 *
 */
public class MainMenuSceneImpl implements GenericScene {
	
	private JPanel rootPanel;
	private MenuView menuView;
	
	/**
     * Constructor that set up the menu scene
     * @param rootPanelIn is the {@link JPanel} that will contain the scene
     */
	public MainMenuSceneImpl(JPanel rootPanelIn) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        this.menuView = new MenuView();
        this.rootPanel.add(this.menuView.getPanel(), BorderLayout.CENTER);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setObserver(SceneController listener) {
		this.menuView.setActionObserver((SceneActionObserver) listener);
	}
	
	/**
	 * 
	 * @return the menu view
	 */
	public MenuView getMenuView() {
		
		return this.menuView;
	}
	


}
