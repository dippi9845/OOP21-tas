package main.java.tas.view.scene;

import java.awt.BorderLayout;


import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.model.MenuModel;
import main.java.tas.view.SandboxModeView;
public class SandboxModeScene implements Scene {
	
	private JPanel rootPanel;
	private SandboxModeView View;
	
	/**
     * Constructor that set up the settings scene
     * @param root is the {@link JPanel} that will contain the scene
     */
	public SandboxModeScene(JPanel rootPanelIn, MenuModel menuModel) {
		this.rootPanel = rootPanelIn;
		this.rootPanel.setLayout(new BorderLayout());
        
        this.View = new SandboxModeView(menuModel);
        
        this.rootPanel.add(this.View.getPanel(), BorderLayout.CENTER);
	}
	
	@Override
	public void setObserver(SceneController listener) {
		this.View.setObserver(listener);
	}

	public SandboxModeView getView() {
		return this.View;
	}
}

