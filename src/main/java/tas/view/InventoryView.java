package main.java.tas.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import main.java.tas.controller.SceneActionObserver;
import main.java.tas.model.tower.factory.DefaultTowers;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that builds the inventory view.
 * Class that implements a {@link ViewAction}.
 */
public class InventoryView implements ViewAction {
    
    private final JPanel inventoryCanvas;
    private final JPanel towerButtonsCanvas;
    private final JButton  buttonList[];
    private int nTowers;
    private final HashMap<String, AdaptiveLabel> textLables = new HashMap<String, AdaptiveLabel>();
    private final JPanel labelCanvas = new JPanel(new GridLayout(0,1));
    
    /**
     * Constructor that sets up the inventory view.
     * @param objects a list of the name of the different towers
     */
    public <T extends Enum <T>>InventoryView(Class <T> objects) {
        this.inventoryCanvas = new JPanel(new GridLayout(0,2));
        this.inventoryCanvas.setBackground(new Color(153,102,0));
        
        this.towerButtonsCanvas = new JPanel(new GridLayout(0,1));
        
        this.nTowers = DefaultTowers.values().length;
        
        this.buttonList = new JButton [this.nTowers];
        
        
        List <T> towerNames = Arrays.asList(objects.getEnumConstants());
        for(int i = 0; i< nTowers; i++) {
        	this.buttonList[i] = new JButton(towerNames.get(i).toString());
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
    
    /** {@inheritDoc} */
    @Override
    public void setActionObserver(SceneActionObserver gameController) {
    	for(int i = 0; i< nTowers; i++) {
        	buttonList[i].addActionListener(gameController.getActionListener());
        }
    }
	
	/**
	 * Adds a label to the right of the inventory.
	 * @param txt the string that has to appear on the label
	 * @param id the key of the label in the HashMap
	 */
	public void addTextLabel(String txt, String id) {
		AdaptiveLabel tmpLabel = new AdaptiveLabel();
		tmpLabel.setText(txt);
		tmpLabel.setFont("Verdana", 1, 20);
		tmpLabel.setForeground(Color.BLACK);
		this.labelCanvas.add(tmpLabel);
		this.textLables.put(id, tmpLabel);
	}
	
	/**
	 * @return the HashMap containing all the labels
	 */
	public HashMap<String, AdaptiveLabel> getInventoryLabels(){
		return this.textLables;
	}
	
	/**
	 * Gets a label from the inventory labels.
	 * @param key the key of the label
	 * @return the label corresponding to the asked key
	 */
	public JLabel getTextLabel(String key) {
		return this.textLables.get(key);
	}

	

	

}
