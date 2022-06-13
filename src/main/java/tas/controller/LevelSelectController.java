package main.java.tas.controller;

import java.awt.event.ActionListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.scene.LevelSelectSceneImpl;
import main.java.tas.view.scene.Scene;

/**
 * Class that creates a controller for the level select menu
 * Class that implements {@link SceneController}.
 */
public class LevelSelectController implements SceneController {
	
	private LevelSelectListener listener;
	private Scene scene;
	private MenuModel model;
	
	/**
	 * Constructor that creates a menu controller for the level select menu.
	 * @param sceneIn the level select scene
	 * @param theModel the menu model
	 */
	public LevelSelectController(Scene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((LevelSelectSceneImpl) scene).setObserver(this);
		this.listener = new LevelSelectListener();
		this.model = theModel;
	}
	
	/**
	 * 
	 * @return the menu model
	 */
	public MenuModel getModel() {
		return this.model;
	}
	
	/** {@inheritDoc} */
	@Override
	public void nextTick() {
		if (this.listener.checkUpdate()) {
			this.model.setCurrentLevel(listener.getCommand());
			this.model.setMainScene(2);
			listener.resetUpdate();
		}
		
	}
	
	/**
	 * 
	 * @return the listener
	 */
	public ActionListener getListener() {
		return this.listener;
	}
	

}
