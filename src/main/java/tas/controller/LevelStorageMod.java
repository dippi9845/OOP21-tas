package main.java.tas.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import main.java.tas.view.TileButton;

public class LevelStorageMod {
	
	private int nTilesSelected;
	
	public LevelStorageMod(int nTilesSelectedIn) {
		this.nTilesSelected = nTilesSelectedIn;
		
	}
		
		
	
	public void addLevel(TileButton buttons []) {

			
			//I turn the final_tile_selected into the home tile (type = 3)
			//TODO last_tile_selected.SetType(3);
			//I save the map in a char map_cheking[xDim][yDim] and check if the map is a valid one
			boolean mistake_made = false;
			int xDim = 25;
			int yDim = 25;
			int position = 0;
			int current_x = 0;
			int current_y = 0;
			int map_checking [][] = new int [xDim][yDim];
			int updated_map_checking [][] = new int [xDim][yDim];
			
			//filling up map_cheking with the types of each tile. saving the position of the starting
			//tile so I don't have to search for it again afterwards
			for(int x = 0 ; x < xDim ; x++) {
				for(int y = 0 ; y < yDim ; y++, position++) {
					map_checking[x][y] = buttons[position].getType();
					updated_map_checking[x][y] = map_checking[x][y];
					if (map_checking[x][y] == 4) {
						current_x = x;
						current_y = y;
					}
				}
			}
			
			//checked_tiles keeps track of how many tiles have been checked, so that I can compare
			//it to n_selected_tiles to check if all tiles have been taken account of when checking
			//if the map is valid
			int checked_tiles = 1;
			
			while(updated_map_checking[current_x][current_y] != 3) {
				
				//i update the current tile to 1, so that the path does not go back on itself
				updated_map_checking[current_x][current_y] = 1;
				
				//first i check if there are adiacent tiles that are of type 2
				if(updated_map_checking[current_x][current_y+1] == 2) {
					current_y++;
					checked_tiles++;
				}
				else if (updated_map_checking[current_x+1][current_y] == 2) {
					current_x++;
					checked_tiles++;
				}
				else if (updated_map_checking[current_x][current_y-1] == 2) {
					current_y--;
					checked_tiles++;
				}
				else if (updated_map_checking[current_x-1][current_y] == 2) {
					current_x--;
					checked_tiles++;
				}
				
				//then i check if i have reached the home tile (type 3)
				else if(updated_map_checking[current_x][current_y+1] == 3) {
					current_y++;
					checked_tiles++;
				}
				else if (updated_map_checking[current_x+1][current_y] == 3) {
					current_x++;
					checked_tiles++;
				}
				else if (updated_map_checking[current_x][current_y-1] == 3) {
					current_y--;
					checked_tiles++;
				}
				else if (updated_map_checking[current_x-1][current_y] == 3) {
					current_x--;
					checked_tiles++;
				}
				else {
					if(!mistake_made) {
						//new UnvalidMapPage(Main.getXDim(), Main.getYDim());
						//this.dispose();
						mistake_made = true;
					}
					break;
				}
			}
			
			//i check if all tiles have been used.
			if(nTilesSelected != checked_tiles) {
				if(!mistake_made) {
					//new UnvalidMapPage(Main.getXDim(), Main.getYDim());
					//this.dispose();
					mistake_made = true;
				}
			}
			
			//now I save the map to level_storage.txt. I also check if mistakes have been made up to this point
			if (!mistake_made) {
				//I save the new_map to a string
				String new_map = "";
				for(int x = 0 ; x < xDim ; x++) {
					for(int y = 0 ; y < yDim ; y++, position++) {
						new_map = new_map + Character.forDigit(map_checking[x][y], 10);
					}
				}
			
				//I read level_storage and save it in full_read_data
				String full_read_data = "";
				try {
					BufferedReader reader = new BufferedReader(new FileReader("res/txt/LevelStorage.txt"));
					String line;
					while((line = reader.readLine()) != null) {
						full_read_data = full_read_data + line;
					}
					reader.close();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			
				//i concatenate the new_level to full_read_data and then write it into level_storage
				full_read_data = full_read_data + new_map;
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter("res/txt/LevelStorage.txt"));
					writer.write(full_read_data);
					writer.close();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}	
		}
	
	public void deleteLevels() {
		
	}
	
}
