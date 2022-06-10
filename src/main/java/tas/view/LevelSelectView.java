package main.java.tas.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.tas.controller.LevelSelectController;
import main.java.tas.controller.SceneController;
import main.java.tas.model.MenuModel;

public class LevelSelectView  {
	
	private JPanel rootPanel = new JPanel(new GridLayout(0, 1, 5, 10));
	private JButton buttonList [];
	private int nLevels;
    
    public LevelSelectView(MenuModel theModel){
    	nLevels = theModel.getNLevels();
    	buttonList = new JButton [nLevels];
    	int counter = 0; 
    	for (JButton button : buttonList) {
    		counter++;
    		button = new JButton(Integer.toString(counter));
    		this.buttonList[counter - 1] = button;
    		this.rootPanel.add(button);
    	}
    }
    
	public JPanel getPanel() {
        return this.rootPanel;
    }
	
	public void setObserver(SceneController levelSelectController) {
		for(int counter = 0; counter < nLevels; counter++) {
			buttonList[counter].addActionListener(((LevelSelectController) levelSelectController).getListener());
		}
    }
}
