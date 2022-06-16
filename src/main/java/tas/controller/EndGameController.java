package main.java.tas.controller;

import java.awt.event.ActionListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.scene.GenericScene;

/**
 * Class that creates a controller for the end game menu.
 * Class that implements {@link SceneActionObserver}.
 */
public class EndGameController implements SceneActionObserver {
	
	private ButtonListener listener = new ButtonListener();
	private GenericScene scene;
	private MenuModel model;
	
	/**
	 * Constructor that creates the end game menu controller, and connects it to its scene.
	 * @param sceneIn the end game menu scene
	 * @param theModel the model
	 */
	public EndGameController(GenericScene sceneIn, MenuModel theModel) {
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
			this.model.setMainScene(1);
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public ActionListener getActionListener() {
		return this.listener;
	}
}