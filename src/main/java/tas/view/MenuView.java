package main.java.tas.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.model.Entity;


/**
 * Class that creates the main menu view.
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
    
    /**
     * 
     * @return the root panel
     */
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
	@Override
	public void setActionObserver(SceneActionObserver menuController) {
        this.newGameButton.addActionListener(menuController.getListener());
        this.settingsButton.addActionListener(menuController.getListener());
        this.sandboxModeButton.addActionListener(menuController.getListener());
        this.exitButton.addActionListener(menuController.getListener());
    }
	
	/**
	 * 
	 * @return the new game button
	 */
	public JButton getNewGameButton() {
		return this.newGameButton;
	}
	
	/**
	 * 
	 * @return the settings button
	 */
	public JButton getSettingsButton() {
		return this.settingsButton;
	}
	
	/**
	 * 
	 * @return the sandbox mode button
	 */
	public JButton getSandboxModeButton() {
		return this.sandboxModeButton;
	}
	
	/**
	 * 
	 * @return the exit button
	 */
	public JButton getExitButton() {
		return this.exitButton;
	}

	@Override
	public void drawEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEntityLabel(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntityLabel(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTextLabel(String text, String id, String anchor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AdaptiveLabel getTextLabel(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTextLabel(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObserver(SceneController observer) {
		// TODO Auto-generated method stub
		
	}
}
