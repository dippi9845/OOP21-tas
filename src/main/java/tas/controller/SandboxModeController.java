package main.java.tas.controller;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import main.java.tas.model.GameSpecs;
import main.java.tas.model.MenuModel;
import main.java.tas.utils.Position;
import main.java.tas.view.scene.SandboxModeScene;
import main.java.tas.view.scene.Scene;

/**
 * Class that implements {@link SceneController}.
 */
public class SandboxModeController implements SceneController {
	
	private SandboxModeListener listener;
	private Scene scene;
	private MenuModel model;
	private final GameSpecs gameSpecs = new GameSpecs();
	private final Color pathColor = new Color(255, 255, 255);
	private final int pathThickness = 50;
	private List <Position> linePositionList = Arrays.asList(new Position(10,10),new Position(200,200));
	private final SettingsListener doneButtonListener = new SettingsListener();
	/**
	 * Constructor that creates a menu controller for the sandbox mode menu.
	 * @param sceneIn the sandbox mode scene
	 * @param theModel the menu model
	 */
	public SandboxModeController(Scene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((SandboxModeScene)this.scene).getView().getGameBoard().setLine(linePositionList, pathColor, pathThickness);
		((SandboxModeScene) scene).setObserver(this);
		((SandboxModeScene) scene).setButtonObserver(this);
		this.listener = new SandboxModeListener();
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
			Position lastSelectedPosition = listener.getLastNodeSelected();
			lastSelectedPosition.positionConverter(this.gameSpecs.getGameUnits(), ((SandboxModeScene)this.scene).getView().getPanel().getPreferredSize());
			this.listener.getNodesSelected().add(lastSelectedPosition);
			this.listener.resetUpdate();
		}
		if (this.doneButtonListener.checkUpdate()) {
			//TODO add the level to the json
			this.model.setMainScene(1);
			this.doneButtonListener.resetUpdate();
		}
	}
	
	/**
	 * 
	 * @return the listener
	 */
	public MouseListener getListener() {
		return this.listener;
	}
	
	/**
	 * 
	 * @return the done button listener
	 */
	public ActionListener getButtonListener() {
		return this.doneButtonListener;
	}
	

}
