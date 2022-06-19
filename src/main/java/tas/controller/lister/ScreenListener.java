package main.java.tas.controller.lister;

import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * Class that listens to the user click on the game board and stores it
 * for the controller to retrieve.
 * The listening parameter is used to check if the listener should update the user click
 * stored.
 * Class that implements {@link MouseListener}.
 * Class that extends {@link GenericListener}.
 *
 */
public class ScreenListener extends GenericListener implements MouseListener{
	
	private Point clickLocation;
	private boolean listening = false;
	
	
	/**
	 * 
	 * @return the click location
	 */
	public Point getClickLocation() {
		return this.clickLocation;
	}
	
	/**
	 * Sets listening to true.
	 */
	public void startListening() {
		this.listening = true;
	}
	
	/**
	 * Sets listening to false.
	 */
	public void stopListening() {
		this.listening = false;
	}
	
	/**
	 * It is not used.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	/**
	 * If the class is listening, it updates the clicked position with the last 
	 * clicked position by the user.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(listening) {
			this.clickLocation = e.getPoint();
			System.out.println(clickLocation.toString());
			setUpdate();
		}
	}

	/**
	 * It is not used.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * It is not used.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * It is not used.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}