package main.java.tas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.tas.model.tower.factory.DefaultTowers;
import javax.swing.AbstractButton;


/**
 * Class that implements {@link ActionListener} used as a listener for the
 * inventory.
 * Class extends {@link GenericListener}.
 */
public class InventoryListener extends GenericListener implements ActionListener{
	
	private DefaultTowers towerSelected;

	/**
	 * @return towerSelected
	 */
	public DefaultTowers getTowerSelected() {
		return this.towerSelected;
	}
	
	/** {@inheritDoc} */
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTowers towerList [] = DefaultTowers.values();
		for(int i = 0; i < towerList.length; i++) {
			if(((AbstractButton) e.getSource()).getText().equals(towerList[i].toString())) {
				setUpdate();
				this.towerSelected = towerList[i];
			}
		}
	}
}