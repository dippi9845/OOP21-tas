package main.java.tas.controller.lister;

import main.java.tas.utils.Position;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;



/**
 * Class that listens for the user clicks and stores their position for
 * the controller to retrieve them.
 * Class that implements {@link MouseListener}.
 *
 */
public class SandboxModeListener extends GenericListener implements MouseListener{
	
	private List <Position> nodesSelected = new ArrayList <Position>();
	private Position lastNodeSelected;
	
	/**
	 * @return a list of all the nodes selected
	 */
	public List <Position> getNodesSelected(){
		return this.nodesSelected;
	}
	
	/**
	 * @return the last node that has been added to the path
	 */
	public Position getLastNodeSelected(){
		return this.lastNodeSelected;
	}
	
	/** It is not used */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Puts the Position the user has clicked on in lastNodeSelected.
	 * Adds lastNodeSelected to nosedSelected.
	 * Sets update to true.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		this.lastNodeSelected = new Position(e.getX(),e.getY());
		System.out.println("click percieved");
		setUpdate();
	}

	/** It is not used */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/** It is not used */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/** It is not used */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}