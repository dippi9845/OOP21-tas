package main.java.tas.model;



import java.util.List;

import org.json.JSONObject;

import main.java.tas.utils.JsonUtils;
import main.java.tas.utils.Position;

public class MenuModelImpl implements MenuModel {
	
	private int mainScene = 1;
	private int menuMode = 1;
	private int currentLevel = 0;
	private String jsonLevelPath = "res" + System.getProperty("file.separator") + "levelStorage" + System.getProperty("file.separator") + "levelStorage.json";
	
	public MenuModelImpl() {
		
	}
	
	@Override
	public int getMainScene() {
		return this.mainScene;
	}

	@Override
	public void setMainScene(int newMainScene) {
		this.mainScene = newMainScene;
	}

	@Override
	public int getMenuMode() {
		return this.menuMode;
	}

	@Override
	public void setMenuMode(int newMenuMode) {
		this.menuMode = newMenuMode;
	}

	@Override
	public void setCurrentLevel(int currentLevelIn) {
		this.currentLevel = currentLevelIn;
		
	}

	@Override
	public int getCurrentLevel() {
		return this.currentLevel;	
	}

	@Override
	public int getNLevels() {
		JSONObject json = JsonUtils.getJsonData(this.jsonLevelPath);
		System.out.println(json.length());
		return json.length();
	}
	
	public List<Position> getNodePositions(int nLevel){
		//TODO passare un array di nodi del livello nLevel
		return null;
	}
	
	
	
	
	

}
