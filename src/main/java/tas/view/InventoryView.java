package main.java.tas.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import main.java.tas.model.tower.factory.DefaultTowers;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.java.tas.model.Entity;

/**
 * Class that implements a {@link ViewComponent}.
 */
public class InventoryView implements ViewAction {
    
    private final JPanel inventoryCanvas;
    private final JPanel towerButtonsCanvas;
    private final JButton  buttonList[];
    private int nTowers;
    private final HashMap<String, AdaptiveLabel> textLables = new HashMap<String, AdaptiveLabel>();
    private final JPanel labelCanvas = new JPanel(new GridLayout(0,1));
    
    /**
     * Constructor that sets up the inventory view
     */
    public InventoryView() {
        this.inventoryCanvas = new JPanel(new GridLayout(0,2));
        this.inventoryCanvas.setBackground(new Color(153,102,0));
        
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
    
    /** {@inheritDoc} */
    @Override
    public JPanel getPanel() {
        return this.inventoryCanvas;
    }
    

    @Override
    public void setActionObserver(SceneActionObserver gameController) {
    	for(int i = 0; i< nTowers; i++) {
        	buttonList[i].addActionListener(gameController.getActionListener());
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
	
	/**
	 * adds a label to the right of the inventory
	 * @param txt the string that has to appear on the label
	 * @param id the key of the label in the HashMap
	 */
	public void addInvetoryLabel(String txt, String id) {
		AdaptiveLabel tmpLabel = new AdaptiveLabel();
		tmpLabel.setText(txt);
		tmpLabel.setFont("Verdana", 1, 20);
		tmpLabel.setForeground(Color.BLACK);
		this.labelCanvas.add(tmpLabel);
		this.textLables.put(id, tmpLabel);
		
		
	}
	
	/**
	 * 
	 * @return the HashMap containing all the labels
	 */
	public HashMap<String, AdaptiveLabel> getInventoryLabels(){
		return this.textLables;
	}
	
	/**
	 * 
	 * @param key the key of the label
	 * @return the label corresponding to the asked key
	 */
	public JLabel getInventoryLabel(String key) {
		return this.textLables.get(key);
	}

	

	

}
