package main.java.tas.controller;

import main.java.tas.utils.Position;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;



/**
 * Class that listens for the user clicks and stores their position for
 * the controller to retrieve them
 * Class that implements {@link MouseListener}
 *
 */
public class SandboxModeListener implements MouseListener{
	
	private boolean update = false;
	private List <Position> nodesSelected = new ArrayList <Position>();
	private Position lastNodeSelected;
	
	/**
	 * 
	 * @return update
	 */
	public boolean checkUpdate() {
		return this.update;
	}
	
	/**
	 * Sets update to false.
	 */
	public void resetUpdate() {
		this.update = false;
	}
	
	/**
	 * 
	 * @return a list of all the nodes selected
	 */
	public List <Position> getNodesSelected(){
		return this.nodesSelected;
	}
	
	/**
	 * 
	 * @return the last node that has been added to the path
	 */
	public Position getLastNodeSelected(){
		return this.lastNodeSelected;
	}
	
	/**
	 * Puts the Position the user has cliked on in lastNodeSelected.
	 * Adds lastNodeSelected to nosedSelcted
	 * sets update to true
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//lastNodeSelected ha la posizione non ancora convertita
		this.lastNodeSelected = new Position(e.getX(),e.getY());
		System.out.println("click percieved");
		//synchronized(this.nodesSelected){
			
		//}
		this.update = true;	
	}

	/** It is not used */
	public void mousePressed(MouseEvent e) {
	}

	/** It is not used */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** It is not used */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** It is not used */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}