package main.java.tas.controller;

import java.awt.event.ActionListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.SceneActionObserver;
import main.java.tas.view.scene.ActionScene;
import main.java.tas.view.scene.SettingsSceneImpl;

/**
 * Class that creates a controller for the settings menu
 * Class that implements {@link SceneController}.
 */
public class SettingsController implements SceneController,SceneActionObserver {
	
	private ButtonListener listener = new ButtonListener();
	private ActionScene scene;
	private MenuModel model;
	
	/**
	 * Constructor that creates the settings menu controller, and connects it to its scene.
	 * @param sceneIn the settings menu scene
	 * @param theModel the model
	 */
	public SettingsController(ActionScene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((SettingsSceneImpl) scene).setActionObserver(this);
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
	
	@Override
	public ActionListener getListener() {
		return this.listener;
	}
	

}

