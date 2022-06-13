package main.java.tas.utils;



import java.util.ArrayList;
import java.util.List;

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
	
	public void readLevel(String level) {
		JSONObject json = JsonUtils.getJsonData(PATH);
		List <Position> list = new ArrayList <Position>();
		//JSONArray level = json.getJSONObject(level).getJSONArray()
	}
	
	public void writeLevel() {
		
	}
}
