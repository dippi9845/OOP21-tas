package main.java.tas.controller;

import java.awt.event.ActionListener;
import main.java.tas.view.SandboxModeScene;
import main.java.tas.model.MenuModel;
import main.java.tas.view.GameScene;

public class SandboxModeController implements SceneController {
	
	private SandboxModeListener listener;
	private GameScene scene;
	private MenuModel model;
	private LevelStorageMod mod;
	
	public SandboxModeController(GameScene sceneIn, MenuModel theModel) {
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
			((SandboxModeScene) scene).getView().getButtons().turnOff();
			mod = new LevelStorageMod(this.listener.getNTilesSelected());
			mod.addLevel(((SandboxModeScene) scene).getView().getButtons().getTiles());
			this.model.incNLevels();
		}
	}
	
	public ActionListener getListener() {
		return this.listener;
	}
	

}
