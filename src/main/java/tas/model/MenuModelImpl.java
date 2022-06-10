package main.java.tas.model;


public class MenuModelImpl implements MenuModel {
	
	private int mainScene = 1;
	private int menuMode = 1;
	private int currentLevel = 0;
	private int nLevels = 3;
	
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
		return this.nLevels;
	}

	@Override
	public void setNLevels(int newNLevels) {
		this.nLevels = newNLevels;
	}

	@Override
	public void incNLevels() {
		this.nLevels++;
	}
	
	
	
	
	

}
