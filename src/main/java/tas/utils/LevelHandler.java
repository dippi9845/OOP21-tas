package main.java.tas.utils;



import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * Class that modifies levelStorage.json, or accesses it for data
 *
 */
public class LevelHandler {
	
	private static String PATH = "res" + System.getProperty("file.separator") + "levelStorage" + System.getProperty("file.separator") + "levelStorage.json";
	
	/**
	 * 
	 * @return the number of elements in levelStorage.json (which is the number of levels)
	 */
	public static int getNElements() {
		try {
			JSONObject json = JsonUtils.getJsonData(PATH);
			return json.length();
		}
		catch (Exception e){
			System.out.println("Error: No enemy json file detected!");
			System.out.println(e);
			return 0;
		}
		
	}
	
	/**
	 * reads a level off levelStorage.json
	 * @param level the name of the level
	 * @return a list of the nodes of the level
	 */
	public static List <Position> readLevel(String level) {
		JSONObject json = JsonUtils.getJsonData(PATH);
		List <Position> list = new ArrayList <Position>();

		JSONArray levelNodes = json.getJSONObject(level).getJSONArray("path");
		for(int i = 0; i < levelNodes.length(); i++) {
			list.add(new Position(levelNodes.getJSONObject(i).getInt("x"), levelNodes.getJSONObject(i).getInt("y")));
		}
		return list;
		
	}
	
	/**
	 * adds a level on levelsStorege.json
	 * @param list the list of nodes of the new level
	 */
	public static void writeLevel(List <Position> list) {
		JSONObject file = JsonUtils.getJsonData(PATH);
		JSONObject level = new JSONObject ();
		JSONArray path = new JSONArray();
		for(int i = 0; i < list.size(); i++) {
			JSONObject node = new JSONObject();
			node.put("x", (int) list.get(i).getX());
			node.put("y", (int) list.get(i).getY());
			path.put(node);
		}
		level.put("path",path);
		file.put("level" + Integer.toString(getNElements() + 1), level);
		saveJson(file);
	}
	
	/**
	 * deletes the user made levels
	 */
	public static void deleteUserLevels(){
		JSONObject json = JsonUtils.getJsonData(PATH);
		if(getNElements() > 3) {
			for(int i = 4; i <= (json.length() + 2); i++) {
				json.remove("level" + Integer.toString(i));
			}
			saveJson(json);
		}	
	}
	
	/**
	 * saves the JSON
	 * @param jsonObj the JSON
	 */
	private static void saveJson(JSONObject jsonObj) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(PATH);
			fileWriter.write(jsonObj.toString(4));
			fileWriter.flush();
			fileWriter.close();
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
