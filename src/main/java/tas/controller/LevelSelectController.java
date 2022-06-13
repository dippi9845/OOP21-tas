package main.java.tas.controller;

import java.awt.event.ActionListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.SceneActionObserver;
import main.java.tas.view.scene.LevelSelectSceneImpl;
import main.java.tas.view.scene.ActionScene;

/**
 * Class that creates a controller for the level select menu
 * Class that implements {@link SceneController}.
 */
public class LevelSelectController implements SceneController,SceneActionObserver {
	
	private LevelSelectListener listener;
	private ActionScene scene;
	private MenuModel model;
	
	/**
	 * Constructor that creates a menu controller for the level select menu.
	 * @param sceneIn the level select scene
	 * @param theModel the menu model
	 */
	public LevelSelectController(ActionScene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((LevelSelectSceneImpl) scene).setActionObserver(this);
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
	@Override
	public ActionListener getListener() {
		return this.listener;
	}
	

}
