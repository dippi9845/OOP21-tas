package main.java.tas.controller;

import java.awt.event.ActionListener;
import main.java.tas.view.SettingsSceneImpl;
import main.java.tas.model.MenuModel;
import main.java.tas.view.GameScene;

public class SettingsController implements SceneController {
	
	private SettingsListener listener;
	private GameScene scene;
	private MenuModel model;
	
	public SettingsController(GameScene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((SettingsSceneImpl) scene).setObserver(this);
		this.listener = new SettingsListener();
		this.model = theModel;
	}
	
	public MenuModel getModel() {
		return this.model;
	}
	
	@Override
	public void nextTick() {
		if (this.listener.checkUpdate()) {
			this.model.setMainScene(1);
			listener.resetUpdate();
		}
		
	}
	
	public ActionListener getListener() {
		return this.listener;
	}
	

}

