package main.java.tas.utils;



import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class LevelHandler {
	
	private static String PATH = "res" + System.getProperty("file.separator") + "levelStorage" + System.getProperty("file.separator") + "levelStorage.json";
	
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
	
	public static List <Position> readLevel(String level) {
		JSONObject json = JsonUtils.getJsonData(PATH);
		List <Position> list = new ArrayList <Position>();
		JSONArray levelNodes = json.getJSONObject(level).getJSONArray("path");
		for(int i = 0; i < levelNodes.length(); i++) {
			list.add(new Position(levelNodes.getJSONObject(i).getDouble("x"), levelNodes.getJSONObject(i).getDouble("y")));
		}
		return list;
		
	}
	
	public static void writeLevel(List <Position> list) {
		JSONObject file = JsonUtils.getJsonData(PATH);
		JSONObject level = new JSONObject ();
		JSONArray path = new JSONArray();
		for(int i = 0; i < list.size(); i++) {
			JSONObject node = new JSONObject();
			node.put("x", list.get(i).getX());
			node.put("y", list.get(i).getY());
			path.put(node);
		}
		level.put("path",path);
		file.put("level" + Integer.toString(getNElements() + 1), level);
		saveJson(file);
	}
	
/*	public static void deleteUserLevels(){
		JSONObject json = JsonUtils.getJsonData(PATH);
		List <Position> list = new ArrayList <Position>();
		JSONArray levelNodes = json.getJSONObject(level).getJSONArray("path");
		for(int i = 0; i < levelNodes.length(); i++) {
			list.add(new Position(levelNodes.getJSONObject(i).getDouble("x"), levelNodes.getJSONObject(i).getDouble("y")));
		}
	}*/
	
	private static void saveJson(JSONObject jsonObj) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(PATH);
			fileWriter.write(jsonObj.toString());
			fileWriter.flush();
			fileWriter.close();
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
