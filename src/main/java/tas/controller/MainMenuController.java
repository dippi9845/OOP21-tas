package main.java.tas.controller;

import java.awt.event.ActionListener;

import main.java.tas.controller.lister.MainMenuListener;
import main.java.tas.model.menu.MenuModel;
import main.java.tas.view.scene.GenericScene;
import main.java.tas.view.scene.MainMenuScene;

/**
 * Class that creates the main menu controller. Class that implements
 * {@link SceneActionObserver} and {@link SceneController}.
 */
public class MainMenuController implements SceneActionObserver, SceneController {

	private MainMenuListener listener;
	private GenericScene scene;
	private MenuModel model;

	/**
	 * Constructor that creates a menu controller for the main menu.
	 * 
	 * @param sceneIn  the menu scene
	 * @param theModel the menu model
	 */
	public MainMenuController(GenericScene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		// ((MainMenuSceneImpl) scene).setObserver(this);
		this.listener = new MainMenuListener(((MainMenuScene) scene).getMenuView());
		this.model = theModel;
	}

	/**
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
	 * @return the listener
	 */
	@Override
	public ActionListener getActionListener() {
		return this.listener;
	}

}
