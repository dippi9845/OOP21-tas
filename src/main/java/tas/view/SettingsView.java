package main.java.tas.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.tas.model.Entity;
import main.java.tas.model.MenuModel;

/**
 * Class that builds the settings menu view.
 */
public class SettingsView implements ViewAction {
	
	private JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private JLabel label;
	private JLabel label2;
	private JButton backButton;
    
	/**
	 * Constructor that builds the settings menu view
	 * @param theModel the model
	 */
    public SettingsView(MenuModel theModel){
    	label = new JLabel("Sorry there are no settings to mess with.");
    	label.setFont(new Font("Serif", Font.PLAIN, 30));
    	label.setHorizontalAlignment(JLabel.CENTER);
    	this.rootPanel.add(label);
    	label2 = new JLabel("This isn't Elden Ring.");
    	label2.setFont(new Font("Serif", Font.PLAIN, 30));
    	label2.setHorizontalAlignment(JLabel.CENTER);
    	this.rootPanel.add(label2);
    	backButton = new JButton("OK GO BACK THEN");
    	this.rootPanel.add(backButton);
    }
    
    /**
     * 
     * @return the root panel
     */
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
	@Override
	public void setActionObserver(SceneActionObserver settingsController) {
		backButton.addActionListener(settingsController.getActionListener());
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


}