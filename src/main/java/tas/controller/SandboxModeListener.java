package main.java.tas.controller;

import main.java.tas.utils.Position;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;




public class SandboxModeListener implements MouseListener{
	
	private boolean update = false;
	private List <Position> nodesSelected;
	private Position lastNodeSelected;
	
	public boolean checkUpdate() {
		return this.update;
	}
	
	public void resetUpdate() {
		this.update = false;
	}
	
	public List <Position> getNodesSelected(){
		return this.nodesSelected;
	}
	
	public Position getLastNodeSelected(){
		return this.lastNodeSelected;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//lastNodeSelected ha la posizione non ancora convertita
		this.lastNodeSelected = new Position(e.getX(),e.getY());
		//synchronized(this.nodesSelected){
			
		//}
		this.nodesSelected.add(lastNodeSelected);
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