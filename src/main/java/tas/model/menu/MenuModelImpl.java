package main.java.tas.model.menu;

import main.java.tas.utils.LevelHandler;

/**
 * Class that implements {@link MenuModel}.
 */
public class MenuModelImpl implements MenuModel {

	private int maxLevels = 6;
	private int mainScene = 1;
	private int menuMode = 1;
	private int currentLevel = 0;

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
		return LevelHandler.getNElements();
	}

	/** {@inheritDoc} */
	@Override
	public int getMaxLevels() {
		return this.maxLevels;
	}

}
