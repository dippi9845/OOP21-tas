package main.java.tas.controller;


import java.awt.event.ActionListener;
import main.java.tas.view.MainMenuSceneImpl;
import main.java.tas.view.Scene;
import main.java.tas.model.MenuModel;
import main.java.tas.view.GameScene;

public class MainMenuController implements SceneController {
	
	private MainMenuListener listener;
	private Scene scene;
	private MenuModel model;
	
	public MainMenuController(Scene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		//((MainMenuSceneImpl) scene).setObserver(this);
		this.listener = new MainMenuListener(((MainMenuSceneImpl) scene).getMenuView());
		this.model = theModel;
	}
	
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
	
	public ActionListener getListener() {
		return this.listener;
	}
	

}
