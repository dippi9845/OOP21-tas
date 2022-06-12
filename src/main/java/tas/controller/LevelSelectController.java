package main.java.tas.controller;

import java.awt.event.ActionListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.scene.LevelSelectSceneImpl;
import main.java.tas.view.scene.Scene;

public class LevelSelectController implements SceneController {
	
	private LevelSelectListener listener;
	private Scene scene;
	private MenuModel model;
	
	public LevelSelectController(Scene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((LevelSelectSceneImpl) scene).setObserver(this);
		this.listener = new LevelSelectListener();
		this.model = theModel;
	}
	
	public MenuModel getModel() {
		return this.model;
	}
	
	@Override
	public void nextTick() {
		if (this.listener.checkUpdate()) {
			this.model.setCurrentLevel(listener.getCommand());
			this.model.setMainScene(2);
			listener.resetUpdate();
		}
		
	}
	
	public ActionListener getListener() {
		return this.listener;
	}
	

}
