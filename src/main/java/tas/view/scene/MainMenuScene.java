package main.java.tas.view.scene;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.controller.observer.SceneActionObserver;
import main.java.tas.view.GenericView;
import main.java.tas.view.MenuView;

/**
 * Class that builds the main menu scene. Class that implements
 * {@link GenericScene}.
 */
public class MainMenuScene implements GenericScene {

	private JPanel rootPanel;
	private MenuView menuView;

	/**
	 * Constructor that set up the menu scene.
	 * 
	 * @param rootPanelIn is the {@link JPanel} that will contain the scene
	 */
	public MainMenuScene(JPanel rootPanelIn) {
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

	/** {@inheritDoc} */
	@Override
	public GenericView getView() {
		return this.menuView;
	}
}
