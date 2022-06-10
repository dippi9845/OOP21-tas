package main.java.tas.model;

public interface MenuModel {
	
	public int getMainScene();
	
	public void setMainScene(int newMainScene);
	
	public int getMenuMode();
	 
	public void setMenuMode(int newMenuMode);
	
	public void setCurrentLevel(int currentLevelIn);
	
	public int getCurrentLevel();
	
	public int getNLevels();
	
	public void setNLevels(int newNLevels);
	
	public void incNLevels();
}
