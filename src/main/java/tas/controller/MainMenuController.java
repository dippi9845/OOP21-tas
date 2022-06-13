package main.java.tas.controller;


import java.awt.event.ActionListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.SceneActionObserver;
import main.java.tas.view.scene.MainMenuSceneImpl;
import main.java.tas.view.scene.ActionScene;

/**
 * Class that implements {@link SceneController}.
 */
public class MainMenuController implements SceneController,SceneActionObserver {
	
	private MainMenuListener listener;
	private ActionScene scene;
	private MenuModel model;
	
	/**
	 * Constructor that creates a menu controller for the main menu.
	 * @param sceneIn the menu scene
	 * @param theModel the menu model
	 */
	public MainMenuController(ActionScene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		//((MainMenuSceneImpl) scene).setObserver(this);
		this.listener = new MainMenuListener(((MainMenuSceneImpl) scene).getMenuView());
		this.model = theModel;
	}
	
	/**
	 * 
	 * @return the model
	 */
	public MenuModel getModel() {
		return this.model;
	}
	
	@Override
	public void nextTick() {
		if (this.listener.checkUpdate()) {
			if (this.listener.getCommand() == 1) {
				this.model.setMainScene(3);
			}
			if (this.listener.getCommand() == 2) {
				this.model.setMainScene(5);
			}
			if (this.listener.getCommand() == 4) {
				this.model.setMainScene(4);
			}
			if (this.listener.getCommand() == 3) {
				this.model.setMainScene(6);
			}
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
