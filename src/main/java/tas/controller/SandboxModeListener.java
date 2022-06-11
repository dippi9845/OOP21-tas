package main.java.tas.controller;

import main.java.tas.utils.Position;
import main.java.tas.view.TileButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;


public class SandboxModeListener implements MouseListener{
	
	private boolean update = false;
	private Position nodeSelected[] ;
	
	public boolean checkUpdate() {
		return this.update;
	}
	
	public void resetUpdate() {
		this.update = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//TODO aggiungi la posizione alla lista di nodi e ritorna anche la posizione (oltre a update = true)
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}