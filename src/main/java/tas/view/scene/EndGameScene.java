package main.java.tas.view.scene;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;
import main.java.tas.controller.SceneController;
import main.java.tas.view.EndGameView;

/**
 * Class that builds the end game menu scene.
 * Class that implements {@link GenericScene}.
 */
public class EndGameScene implements GenericScene {
	
	private JPanel rootPanel;
	private EndGameView View;
	
	/**
     * Constructor that set up the settings scene.
     * 
     * @param rootPanelIn is the {@link JPanel} that will contain the scene
     */
	public EndGameScene(JPanel rootPanelIn) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        this.View = new EndGameView();
        this.rootPanel.add(this.View.getPanel(), BorderLayout.CENTER);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setObserver(SceneController listener) {
		this.View.setActionObserver((SceneActionObserver) listener);
	}

	/**
	 * @return the settings view
	 */
	public EndGameView getView() {
		return this.View;
	}
}
