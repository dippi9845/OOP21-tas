package main.java.tas.view.scene;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.controller.observer.SceneActionObserver;
import main.java.tas.view.FullLevelsView;

/**
 * Class that builds the levels full menu scene.
 * Class that implements {@link GenericScene}.
 */
public class FullLevelsScene implements GenericScene {
	
	private JPanel rootPanel;
	private FullLevelsView View;
	
	/**
     * Constructor that set up the settings scene.
     * @param rootPanelIn is the {@link JPanel} that will contain the scene
     */
	public FullLevelsScene(JPanel rootPanelIn) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        this.View = new FullLevelsView();
        this.rootPanel.add(this.View.getPanel(), BorderLayout.CENTER);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setObserver(SceneController listener) {
		this.View.setActionObserver((SceneActionObserver) listener);
	}

	/** {@inheritDoc} */
	@Override
	public FullLevelsView getView() {
		return this.View;
	}
}