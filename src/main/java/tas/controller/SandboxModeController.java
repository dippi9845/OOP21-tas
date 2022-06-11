package main.java.tas.controller;


import java.awt.event.MouseListener;

import main.java.tas.model.MenuModel;
import main.java.tas.view.scene.GameScene;
import main.java.tas.view.scene.SandboxModeScene;
import main.java.tas.view.scene.Scene;

public class SandboxModeController implements SceneController {
	
	private SandboxModeListener listener;
	private Scene scene;
	private MenuModel model;
	
	public SandboxModeController(Scene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((SandboxModeScene) scene).setObserver(this);
		this.listener = new SandboxModeListener();
		this.model = theModel;
	}
	
	public MenuModel getModel() {
		return this.model;
	}
	
	@Override
	public void nextTick() {
		if (this.listener.checkUpdate()) {
		
			
		}
	}
	
	public MouseListener getListener() {
		return this.listener;
	}
	

}
