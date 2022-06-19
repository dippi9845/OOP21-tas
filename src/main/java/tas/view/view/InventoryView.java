package main.java.tas.view.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import main.java.tas.controller.SceneActionObserver;
import main.java.tas.model.tower.factory.DefaultTowers;
import main.java.tas.view.ViewAction;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that builds the inventory view.
 * Class that implements a {@link ViewAction}.
 * @param <T>
 */
public class InventoryView implements ViewAction {
    
    private final JPanel inventoryCanvas;
    private final JPanel towerButtonsCanvas;
    private final HashMap<String, JButton> buttonList = new HashMap<String, JButton>();
    private final int nTowers;
    private final HashMap<String, JLabel> textLables = new HashMap<String, JLabel>();
    private final JPanel labelCanvas = new JPanel(new GridLayout(0,1));
    private List <String> towerNames = new ArrayList <String>();
    
    /**
     * Constructor that sets up the inventory view.
     * @param objects a list of the name of the different towers
     */
    public <T extends Enum <T>>InventoryView(Class <T> objects) {
        this.inventoryCanvas = new JPanel(new GridLayout(0,2));
        this.inventoryCanvas.setBackground(new Color(153,102,0));
        this.towerButtonsCanvas = new JPanel(new GridLayout(0,1));
        this.nTowers = DefaultTowers.values().length;
        List <T> towerNamesIn = Arrays.asList(objects.getEnumConstants());
        for(int i = 0; i< nTowers; i++) {
        	String tower = towerNamesIn.get(i).toString();
        	this.buttonList.put((tower), new JButton(tower));
        	this.towerButtonsCanvas.add(buttonList.get(tower));
        	this.towerNames.add(tower.toString());
        }
        this.inventoryCanvas.add(towerButtonsCanvas);
        this.inventoryCanvas.add(labelCanvas); 
    }
    
    public void disableButtons(List <String> names) {
    	List <String> towersToEnable = new ArrayList <String> ();
    	for(String name : this.towerNames) {
    		towersToEnable.add(name);
    	}
    	for(String name : names) {
    		this.buttonList.get(name).setEnabled(false);
    		towersToEnable.remove(name);
    	}
    	for(String name : towersToEnable) {
    		this.buttonList.get(name).setEnabled(true);
    	}
    }
    
    /** {@inheritDoc} */
    @Override
    public JPanel getPanel() {
        return this.inventoryCanvas;
    }
    
    /** {@inheritDoc} */
    @Override
    public void setActionObserver(SceneActionObserver gameController) {
    	for(JButton button : buttonList.values()) {
        	button.addActionListener(gameController.getActionListener());
        }
    }
	
	/**
	 * Adds a label to the right of the inventory.
	 * 
	 * @param txt the string that has to appear on the label
	 * @param id the key of the label in the HashMap
	 */
	public void addTextLabel(String txt, String id) {
		JLabel tmpLabel = new JLabel();
		tmpLabel.setText(txt);
		tmpLabel.setFont(new Font("Verdana", 1, 20));
		tmpLabel.setForeground(Color.BLACK);
		this.labelCanvas.add(tmpLabel);
		this.textLables.put(id, tmpLabel);
	}
	
	/**
	 * @return the HashMap containing all the labels
	 */
	public HashMap<String, JLabel> getInventoryLabels(){
		return this.textLables;
	}
	
	/**
	 * Gets a label from the inventory labels.
	 * 
	 * @param key the key of the label
	 * @return the label corresponding to the asked key
	 */
	public JLabel getTextLabel(String key) {
		return this.textLables.get(key);
	}
	
	/**
	 * Resets the color of all the buttons.
	 */
	public void resetButtonBackground() {
		for(JButton button : buttonList.values()) {
			button.setBackground(null);
		}
	}
	
	/**
	 * Turns the selected button red.
	 * 
	 * @param name the button
	 */
	public void selectButton(String name) {
		this.buttonList.get(name).setBackground(Color.RED);
	};

	

	

}
