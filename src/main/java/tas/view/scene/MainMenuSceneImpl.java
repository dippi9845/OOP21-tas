package main.java.tas.view.scene;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import main.java.tas.view.MenuView;
import main.java.tas.view.SceneActionObserver;

/**
 * 
 * Class that implements {@link ActionScene}.
 *
 */
public class MainMenuSceneImpl implements ActionScene {
	
	private JPanel rootPanel;
	private MenuView menuView;
	
	/**
     * Constructor that set up the menu scene
     * @param root is the {@link JPanel} that will contain the scene
     */
	public MainMenuSceneImpl(JPanel rootPanelIn) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        this.menuView = new MenuView();
        this.rootPanel.add(this.menuView.getPanel(), BorderLayout.CENTER);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setActionObserver(SceneActionObserver listener) {
		this.menuView.setActionObserver(listener);
	}
	
	/**
	 * 
	 * @return the menu view
	 */
	public MenuView getMenuView() {
		
		return this.menuView;
	}
	


}
