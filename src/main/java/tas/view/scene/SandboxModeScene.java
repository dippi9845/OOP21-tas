package main.java.tas.view.scene;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.model.MenuModel;
import main.java.tas.view.SandboxModeView;
import main.java.tas.controller.SandboxModeController;

/**
 * Class that builds the sandbox mode scene.
 * Class that implements {@link Scene}.
 */
public class SandboxModeScene implements Scene {
	
	private JPanel rootPanel;
	private SandboxModeView View;
	
	/**
     * Constructor that set up the sandbox mode scene.
     * @param rootPanelIn is the {@link JPanel} that will contain the scene
     * @param menuModel the menu model
     */
	public SandboxModeScene(JPanel rootPanelIn, MenuModel menuModel) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        this.View = new SandboxModeView(menuModel);
        this.rootPanel.add(this.View.getPanel(), BorderLayout.CENTER);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setObserver(SceneController listener) {
		this.View.getGameBoard().addMouseListener(((SandboxModeController)listener).getListener());
	}
	
	/**
	 * sets an observer for the sandbox mode view
	 * @param listener the observer
	 */
	public void setButtonObserver(SceneController listener) {
		this.View.setButtonObserver(listener);
	}

	/**
	 * 
	 * @return the sandbox mode view
	 */
	public SandboxModeView getView() {
		return this.View;
	}
}

