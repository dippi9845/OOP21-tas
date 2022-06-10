package main.java.tas.view;

import java.awt.BorderLayout;


import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.model.MenuModel;
public class SettingsSceneImpl implements GameScene {
	
	private JPanel rootPanel;
	private SettingsView View;
	
	/**
     * Constructor that set up the settings scene
     * @param root is the {@link JPanel} that will contain the scene
     */
	public SettingsSceneImpl(JPanel rootPanelIn, MenuModel menuModel) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        
        this.View = new SettingsView(menuModel);
        
        this.rootPanel.add(this.View.getPanel(), BorderLayout.CENTER);
	}
	@Override
	public void setObserver(SceneController listener) {
		this.View.setObserver(listener);
	}

	public SettingsView getView() {
		
		return this.View;
	}
}
