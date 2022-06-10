package main.java.tas.controller;

import main.java.tas.view.TileButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;


public class SandboxModeListener implements ActionListener{
	
	private boolean update = false;
	private boolean firstTileSelected = false;
	private TileButton lastTileSelected;
	private int nTilesSelected = 0;
	
	public boolean checkUpdate() {
		return this.update;
	}
	
	public void resetUpdate() {
		this.update = false;
	}
	
	public TileButton getLastTileSelected() {
		return this.lastTileSelected;
	}
	
	public int getNTilesSelected() {
		return this.nTilesSelected;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((AbstractButton)e.getSource()).getText() == "DONE") {
			update = true;
		}
		else if(!firstTileSelected) {
			lastTileSelected = ((TileButton)e.getSource());
			((TileButton) e.getSource()).setType(3);
			nTilesSelected++;
			firstTileSelected = true;
		}
		else {
			lastTileSelected = ((TileButton)e.getSource());
			((TileButton) e.getSource()).setType(2);
			nTilesSelected++;
		}
	}
}