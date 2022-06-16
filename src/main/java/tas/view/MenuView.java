package main.java.tas.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;


/**
 * Class that builds the main menu view.
 * Class that implements a {@link ViewAction}.
 */
public class MenuView implements ViewAction {
	
	private JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private JButton newGameButton = new JButton("NEW GAME");
	private JButton sandboxModeButton = new JButton("SANDBOX MODE");
	private JButton	settingsButton = new JButton("SETTINGS");
    private JButton exitButton = new JButton("EXIT");
    
    /**
     * Constructor that builds the main menu view.
     */
    public MenuView(){
    	this.rootPanel.add(this.newGameButton);
    	this.rootPanel.add(this.sandboxModeButton);
    	this.rootPanel.add(this.settingsButton);
    	this.rootPanel.add(this.exitButton);
    }
    
    /** {@inheritDoc} */
    @Override
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
    /** {@inheritDoc} */
    @Override
	public void setActionObserver(SceneActionObserver menuController) {
        this.newGameButton.addActionListener(menuController.getActionListener());
        this.settingsButton.addActionListener(menuController.getActionListener());
        this.sandboxModeButton.addActionListener(menuController.getActionListener());
        this.exitButton.addActionListener(menuController.getActionListener());
    }
	
	/**
	 * @return the new game button
	 */
	public JButton getNewGameButton() {
		return this.newGameButton;
	}
	
	/**
	 * @return the settings button
	 */
	public JButton getSettingsButton() {
		return this.settingsButton;
	}
	
	/**
	 * @return the sandbox mode button
	 */
	public JButton getSandboxModeButton() {
		return this.sandboxModeButton;
	}
	
	/**
	 * @return the exit button
	 */
	public JButton getExitButton() {
		return this.exitButton;
	}
}