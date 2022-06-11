package main.java.tas.controller;

import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.event.MouseEvent;


public class ScreenListener implements MouseListener{
	
	private boolean update = false;
	private Point clickLocation;
	
	public boolean checkUpdate() {
		return this.update;
	}
	
	public void resetUpdate() {
		this.update = false;
	}
	
	public Point getClickLocation() {
		return this.clickLocation;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.clickLocation = e.getPoint();
		System.out.println(clickLocation.toString());
		this.update = true;
		
		
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