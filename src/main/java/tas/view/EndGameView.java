package main.java.tas.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.tas.controller.SceneActionObserver;


/**
 * Class that builds the end game view.
 */
public class EndGameView implements ViewAction {
	
	private JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private JLabel label;
	private JButton backButton;
    
	/**
	 * Constructor that builds the settings menu view
	 */
    public EndGameView(){
    	
    	//TODO aggiungere lo score
    	label = new JLabel("You lost. Your score is 1");
    	label.setFont(new Font("Serif", Font.PLAIN, 30));
    	label.setHorizontalAlignment(JLabel.CENTER);
    	this.rootPanel.add(label);
    	backButton = new JButton("BACK TO MAIN MENU");
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
	public void setActionObserver(SceneActionObserver observer) {
		backButton.addActionListener(observer.getActionListener());
    }

}
