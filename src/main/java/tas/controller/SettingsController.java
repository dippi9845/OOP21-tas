package main.java.tas.controller;

import java.awt.event.ActionListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.scene.Scene;
import main.java.tas.view.scene.SettingsSceneImpl;

/**
 * Class that creates a controller for the settings menu
 * Class that implements {@link SceneController}.
 */
public class SettingsController implements SceneController {
	
	private SettingsListener listener;
	private Scene scene;
	private MenuModel model;
	
	/**
	 * Constructor that creates the settings menu controller, and connects it to its scene.
	 * @param sceneIn the settings menu scene
	 * @param theModel the model
	 */
	public SettingsController(Scene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((SettingsSceneImpl) scene).setObserver(this);
		this.listener = new SettingsListener();
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
			this.model.setMainScene(1);
			listener.resetUpdate();
		}
		
	}
	
	/**
	 * 
	 * @return listener
	 */
	public ActionListener getListener() {
		return this.listener;
	}
	

}

