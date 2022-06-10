package main.java.tas.view;


import java.awt.event.ActionListener;
import javax.swing.*;



public class TileFactory  {
	
	public TileButton map_buttons[];
	
		//constructor begins
		public TileFactory(int level_n) {

			//I use 25 as map dimension
			int totMapDim = 625;
			
			//I initialize map_buttons give it the dimension of totMapDim (the dimension of the map)
			map_buttons = new TileButton[totMapDim];
			
			//I initialize every TileButton(0) in map_buttons 
			
			for(int position = 0 ; position < totMapDim; position++) {
				map_buttons[position] = new TileButton(1);
			}
		}

		
		
		//listing methods
		
		//adds all TileButtons in map_buttons to JPanel panel
		public void addToPannel (JPanel panel) {
			for (int i = 0; i < 625; i++) {
				panel.add(map_buttons[i]);
			}
			
		}
		
		// adds an action listener to every button in the ActionListener listener
		public void addToActionListener (ActionListener listener) {
			for (JButton button : map_buttons) {
				button.addActionListener(listener);
			}
		}
		
		//turns off all buttons
		public void turnOff(){
			for (JButton button : map_buttons) {
				button.setEnabled(false);
			}
		}
		
		//returns TileButton map_buttons [position]
		public TileButton getTile (int position) {
			return map_buttons[position];
		}
		
		public TileButton[] getTiles() {
			return map_buttons;
		}
}

