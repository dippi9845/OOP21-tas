package main.java.tas.view;

import java.awt.Color;
import javax.swing.*;

//this class is a modified JButton class which contains the attribute int type and has its background already
//set to the correct terrain color (green for grass, yellow for sand, and red for the base

public class TileButton extends JButton {
	
	private int type;
	
//	constructor begins
	public TileButton (int typeIn) {
		this.type = typeIn;
		if(this.type == 1) {
			this.setBackground(Color.GREEN);
		}
		if(this.type == 2) {
			this.setBackground(Color.YELLOW);
		}
		if(this.type == 3) {
			this.setBackground(Color.RED);
		}
		if(this.type == 4) {
			this.setBackground(Color.ORANGE);
		}
	}
	//GetType return the type of this TileButton. SetType modifiyes the type of this TileButton and changes the 
	//background accordingly
	public int getType(){
		return this.type;
	}
	
	
	public TileButton setType(int new_type) {
		this.type = new_type;
		if(this.type == 1) {
			this.setBackground(Color.GREEN);
		}
		if(this.type == 2) {
			this.setBackground(Color.YELLOW);
		}
		if(this.type == 3) {
			this.setBackground(Color.RED);
		}
		if(this.type == 4) {
			this.setBackground(Color.ORANGE);
		}
		return this;
	}
}

