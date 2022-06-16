package main.java.tas.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;


/**
 * Class that builds the levels full menu view.
 */
public class FullLevelsView implements ViewAction {
	
	private JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private JLabel label;
	private JLabel label2;
	private JButton backButton;
	private JButton deleteButton;
    
	/**
	 * Constructor that builds the settings menu view.
	 */
    public FullLevelsView(){
    	label = new JLabel("You have reached the max number of levels allowed.");
    	label.setFont(new Font("Serif", Font.PLAIN, 30));
    	label.setHorizontalAlignment(JLabel.CENTER);
    	this.rootPanel.add(label);
    	label2 = new JLabel("Select 'DELETE USER LEVELS' to delete all user levels.");
    	label2.setFont(new Font("Serif", Font.PLAIN, 30));
    	label2.setHorizontalAlignment(JLabel.CENTER);
    	this.rootPanel.add(label2);
    	deleteButton = new JButton("DELETE USER LEVELS");
    	this.rootPanel.add(deleteButton);
    	backButton = new JButton("BACK");
    	this.rootPanel.add(backButton);
    }
    
    /** {@inheritDoc} */
    @Override
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
    /** {@inheritDoc} */
    @Override
	public void setActionObserver(SceneActionObserver observer) {
		backButton.addActionListener(observer.getActionListener());
		deleteButton.addActionListener(observer.getActionListener());
    }
}
