package main.java.tas.view.scene;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.model.menu.MenuModel;
import main.java.tas.view.SceneActionObserver;
import main.java.tas.view.SettingsView;

/**
 * Class that builds the sandbox mode scene.
 * Class that implements {@link ActionScene}.
 */
public class SettingsSceneImpl implements GenericScene {
	
	private JPanel rootPanel;
	private SettingsView View;
	
	/**
     * Constructor that set up the settings scene.
     * @param rootPanelIn is the {@link JPanel} that will contain the scene
     * @param menuModel the menu model
     */
	public SettingsSceneImpl(JPanel rootPanelIn, MenuModel menuModel) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        
        this.View = new SettingsView(menuModel);
        
        this.rootPanel.add(this.View.getPanel(), BorderLayout.CENTER);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setObserver(SceneController listener) {
		this.View.setActionObserver((SceneActionObserver) listener);
	}

	/**
	 * 
	 * @return the settings view
	 */
	public SettingsView getView() {
		
		return this.View;
	}
}
