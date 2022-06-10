package main.java.tas.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.controller.MainMenuController;
import main.java.tas.controller.SceneController;

public class MenuView  {
	
	private JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private JButton newGameButton = new JButton("NEW GAME");
	private JButton sandboxModeButton = new JButton("SANDBOX MODE");
	private JButton	settingsButton = new JButton("SETTINGS");
    private JButton exitButton = new JButton("EXIT");
    
    public MenuView(){
    	
    	
    	this.rootPanel.add(this.newGameButton);
    	this.rootPanel.add(this.sandboxModeButton);
    	this.rootPanel.add(this.settingsButton);
    	this.rootPanel.add(this.exitButton);
    }
    
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
	public void setObserver(SceneController menuController) {
        this.newGameButton.addActionListener(((MainMenuController) menuController).getListener());
        this.settingsButton.addActionListener(((MainMenuController) menuController).getListener());
        this.sandboxModeButton.addActionListener(((MainMenuController) menuController).getListener());
        this.exitButton.addActionListener(((MainMenuController) menuController).getListener());
    }
	
	public JButton getNewGameButton() {
		return this.newGameButton;
	}
	
	public JButton getSettingsButton() {
		return this.settingsButton;
	}
	
	public JButton getSandboxModeButton() {
		return this.sandboxModeButton;
	}
	
	public JButton getExitButton() {
		return this.exitButton;
	}
}
