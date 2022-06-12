package main.java.tas.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import main.java.tas.model.tower.factory.DefaultTowers;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.tas.controller.SceneController;
import main.java.tas.controller.GameController;
import main.java.tas.model.Entity;


public class InventoryView implements ViewComponent {
    
    private final JPanel inventoryCanvas;
    private final JPanel towerButtonsCanvas;
    private final JButton  buttonList[];
    private int nTowers;
    private final HashMap<String, AdaptiveLabel> textLables = new HashMap<String, AdaptiveLabel>();
    private final JPanel labelCanvas = new JPanel(new GridLayout(0,1));
    public InventoryView() {
        this.inventoryCanvas = new JPanel(new GridLayout(0,2));
        this.inventoryCanvas.setBackground(new Color(153,102,0));    // testing
        //this.inventoryCanvas.add(new JTextField("INVENTORY"));    // testing
        
        this.towerButtonsCanvas = new JPanel(new GridLayout(0,1));
        
        this.nTowers = DefaultTowers.values().length;
        
        this.buttonList = new JButton [this.nTowers];
        
        
        DefaultTowers towerNames [] = DefaultTowers.values();
        for(int i = 0; i< nTowers; i++) {
        	this.buttonList[i] = new JButton(towerNames [i].toString());
        	this.towerButtonsCanvas.add(buttonList[i]);
        }
        
        this.inventoryCanvas.add(towerButtonsCanvas);
        this.inventoryCanvas.add(labelCanvas);
        
        
        
    }

    //@Override
    public JPanel getPanel() {
        return this.inventoryCanvas;
    }
    


    public void setObserver(SceneController gameController) {
    	for(int i = 0; i< nTowers; i++) {
        	buttonList[i].addActionListener(((GameController) gameController).getListener());
        } 
        
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
	public void setObserver() {
		// TODO Auto-generated method stub
		
	}

	/** {@inheritDoc} */
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
	
	public void addInvetoryLabel(String txt, String id) {
		AdaptiveLabel tmpLabel = new AdaptiveLabel();
		tmpLabel.setText(txt);
		tmpLabel.setFont("Verdana", 1, 20);
		tmpLabel.setForeground(Color.BLACK);
		this.labelCanvas.add(tmpLabel);
		this.textLables.put(id, tmpLabel);
		
		
	}
	
	public HashMap<String, AdaptiveLabel> getInventoryLabels(){
		return this.textLables;
	}
	
	public JLabel getInventoryLabel(String key) {
		return this.textLables.get(key);
	}
	

	

}
