package main.java.tas.model;

import java.util.List;

import org.json.JSONObject;

import main.java.tas.utils.JsonUtils;
import main.java.tas.utils.Position;

/**
 * Class that implements {@link MenuModel}.
 */
public class MenuModelImpl implements MenuModel {
	
	private int mainScene = 1;
	private int menuMode = 1;
	private int currentLevel = 0;
	private String jsonLevelPath = "res" + System.getProperty("file.separator") + "levelStorage" + System.getProperty("file.separator") + "levelStorage.json";
	
	/** {@inheritDoc} */
	@Override
	public int getMainScene() {
		return this.mainScene;
	}

	/** {@inheritDoc} */
	@Override
	public void setMainScene(int newMainScene) {
		this.mainScene = newMainScene;
	}

	/** {@inheritDoc} */
	@Override
	public int getMenuMode() {
		return this.menuMode;
	}

	/** {@inheritDoc} */
	@Override
	public void setMenuMode(int newMenuMode) {
		this.menuMode = newMenuMode;
	}

	/** {@inheritDoc} */
	@Override
	public void setCurrentLevel(int currentLevelIn) {
		this.currentLevel = currentLevelIn;
		
	}

	/** {@inheritDoc} */
	@Override
	public int getCurrentLevel() {
		return this.currentLevel;	
	}

	/** {@inheritDoc} */
	@Override
	public int getNLevels() {
		JSONObject json = JsonUtils.getJsonData(this.jsonLevelPath);
		System.out.println(json.length());
		return json.length();
	}
	
	/** {@inheritDoc} */
	public List<Position> getNodePositions(int nLevel){
		//TODO passare un array di nodi del livello nLevel
		return null;
	}
	
	
	
	
	

}
