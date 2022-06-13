package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.tas.model.tower.factory.DefaultTowers;
import javax.swing.AbstractButton;


/**
 * Class that implements {@link ActionListener}.
 */
public class InventoryListener implements ActionListener{
	
	private boolean update = false;
	private DefaultTowers towerSelected;
	
	/**
	 * 
	 * @return update
	 */
	public boolean checkUpdate() {
		return this.update;
	}
	
	/**
	 * 
	 * @return towerSelected
	 */
	public DefaultTowers getTowerSelected() {
		return this.towerSelected;
	}
	
	/**
	 * Sets update to false.
	 */
	public void resetUpdate() {
		this.update = false;
	}
	
	/** {@inheritDoc} */
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTowers towerList [] = DefaultTowers.values();
		for(int i = 0; i < towerList.length; i++) {
			if(((AbstractButton) e.getSource()).getText().equals(towerList[i].toString())) {
				this.update = true;
				this.towerSelected = towerList[i];
			}
		}
	}
}