package main.java.tas.view.scene;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.java.tas.model.MenuModel;
import main.java.tas.view.SandboxModeView;
import main.java.tas.view.SceneActionObserver;
import main.java.tas.view.SceneMouseObserver;

/**
 * Class that builds the sandbox mode scene.
 * Class that implements {@link ActionScene}.
 */
public class SandboxModeScene implements MouseScene,ActionScene {
	
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
	public void setMouseObserver(SceneMouseObserver observer) {
		this.View.getGameBoard().addMouseListener(observer.getMouseListener());
	}
	
	/**
	 * sets an observer for the sandbox mode view
	 * @param listener the observer
	 */
	public void setActionObserver(SceneActionObserver listener) {
		this.View.setActionObserver(listener);
	}

	/**
	 * 
	 * @return the sandbox mode view
	 */
	public SandboxModeView getView() {
		return this.View;
	}
}

