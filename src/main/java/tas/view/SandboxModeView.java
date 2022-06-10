package main.java.tas.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.controller.SandboxModeController;
import main.java.tas.controller.SceneController;
import main.java.tas.model.MenuModel;

public class SandboxModeView  {
	
	private JPanel rootPanel = new JPanel(new BorderLayout());
	private TileFactory buttons;
	private JButton finishButton = new JButton("DONE");
	private JPanel panel = new JPanel(new GridLayout(25,25));
    
    public SandboxModeView(MenuModel theModel){
    	
    	
    	buttons = new TileFactory(0);
    	
    	buttons.addToPannel(this.panel);
    	
    	this.rootPanel.add(panel, BorderLayout.CENTER);
    	this.rootPanel.add(finishButton, BorderLayout.SOUTH);
    	
    	
    }
    
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
	public void setObserver(SceneController sandboxModeController) {
		buttons.addToActionListener(((SandboxModeController) sandboxModeController).getListener());
    }
	
	public TileFactory getButtons() {
		return buttons;
	}
}