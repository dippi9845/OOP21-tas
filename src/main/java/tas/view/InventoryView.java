package main.java.tas.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

//import main.java.tas.model.Entity;

public class InventoryView /*implements ViewComponent*/ {
    
    private final JPanel inventoryCanvas;
    private final JPanel towerButtonsCanvas;
    private final JButton tower1Button;
    private final JButton tower2Button;
    private final JButton tower3Button;
    private final JButton tower4Button;
    
    public InventoryView() {
        this.inventoryCanvas = new JPanel(new GridLayout(2,0));
        this.inventoryCanvas.setBackground(new Color(153,102,0));    // testing
        //this.inventoryCanvas.add(new JTextField("INVENTORY"));    // testing
        
        this.towerButtonsCanvas = new JPanel(new GridLayout(0,2));
        
        this.tower1Button = new JButton(/*the image of the tower1*/);
        this.tower2Button = new JButton(/*the image of the tower2*/);
        this.tower3Button = new JButton(/*the image of the tower3*/);
        this.tower4Button = new JButton(/*the image of the tower4*/);
        
        this.towerButtonsCanvas.add(this.tower1Button);
        this.towerButtonsCanvas.add(this.tower2Button);
        this.towerButtonsCanvas.add(this.tower3Button);
        this.towerButtonsCanvas.add(this.tower4Button);
        
        
        this.inventoryCanvas.add(towerButtonsCanvas);
        
        
        
    }

    //@Override
    public JPanel getPanel() {
        return this.inventoryCanvas;
    }


    public void setObserver(ActionListener listener) {
      tower1Button.addActionListener(listener);  
      tower2Button.addActionListener(listener); 
      tower3Button.addActionListener(listener); 
      tower4Button.addActionListener(listener); 
        
    }
/*
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
	public void setObserver() {
		// TODO Auto-generated method stub
		
	}
	*/

}
