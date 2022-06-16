package main.java.tas.controller;

import java.awt.event.ActionListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.scene.GenericScene;
import main.java.tas.utils.LevelHandler;

/**
 * Class that creates a controller for the levels full menu
 * Class that implements {@link SceneActionObserver}.
 */
public class FullLevelsController implements SceneActionObserver {
	
	private FullLevelsListener listener = new FullLevelsListener();
	private GenericScene scene;
	private MenuModel model;
	
	/**
	 * Constructor that creates the full levels menu controller, and connects it to its scene.
	 * @param sceneIn the full levels menu scene
	 * @param theModel the model
	 */
	public FullLevelsController(GenericScene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		scene.setObserver(this);
		this.model = theModel;
	}
	
	/**
	 * 
	 * @return the model
	 */
	public MenuModel getModel() {
		return this.model;
	}
	
	/** {@inheritDoc} */
	@Override
	public void nextTick() {
		if (this.listener.checkUpdate()) {
			if(this.listener.getCommand() == 1) {
				this.model.setMainScene(1);
			}
			if(this.listener.getCommand() == 2) {
				LevelHandler.deleteUserLevels();
				this.model.setMainScene(1);
			}
			listener.resetUpdate();
		}
		
	}
	
	/** {@inheritDoc} */
	@Override
	public ActionListener getActionListener() {
		return this.listener;
	}
	

}