package main.java.tas.controller;

import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * Class that listens to the user click on the game board and stores it
 * for the controller to retrieve.
 * The listening parameter is used to check if the listener should update the user click
 * stored.
 * Class that implements {@link MouseListener}.
 *
 */
public class ScreenListener implements MouseListener{
	
	private boolean update = false;
	private Point clickLocation;
	private boolean listening = false;
	
	/**
	 * 
	 * @return update
	 */
	public boolean checkUpdate() {
		return this.update;
	}
	
	/** 
	 * sets update to false
	 */
	public void resetUpdate() {
		this.update = false;
	}
	
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
	 * If the class is listening, it updates the clicked position with the last 
	 * clicked position by the user.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(listening) {
			this.clickLocation = e.getPoint();
			System.out.println(clickLocation.toString());
			this.update = true;
		}
		
		
		
	}
	
	/**
	 * It is not used.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * It is not used.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * It is not used.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * It is not used.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}