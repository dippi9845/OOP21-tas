package main.java.tas.model;

/** 
 * 
 * Interface for the menu model.
 *
 */
public interface MenuModel {
	
	/**
	 * 
	 * @return mainScene
	 */
	public int getMainScene();
	
	/**
	 * Sets the value of mainScene.
	 * 
	 * @param newMainScene the new value of mainScene
	 */
	public void setMainScene(int newMainScene);
	
	/**
	 * 
	 * @return menuMode
	 */
	public int getMenuMode();
	 
	/**
	 * Sets the value of menuMode.
	 * 
	 * @param newMenuMode the new value of menuMode
	 */
	public void setMenuMode(int newMenuMode);
	
	/**
	 * Sets the value of currentLevel.
	 * 
	 * @param currentLevelIn the new value of currentLevel
	 */
	public void setCurrentLevel(int currentLevelIn);
	
	/**
	 * 
	 * @return currentLevel
	 */
	public int getCurrentLevel();
	
	/**
	 * 
	 * @return the number of levels currently saved in levelStorage.json
	 */
	public int getNLevels();
	
	public int getMaxLevels();
	
}
