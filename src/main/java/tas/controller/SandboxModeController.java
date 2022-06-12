package main.java.tas.controller;


import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import main.java.tas.model.GameSpecs;
import main.java.tas.model.MenuModel;
import main.java.tas.utils.Position;
import main.java.tas.view.scene.SandboxModeScene;
import main.java.tas.view.scene.Scene;

public class SandboxModeController implements SceneController {
	
	private SandboxModeListener listener;
	private Scene scene;
	private MenuModel model;
	private final GameSpecs gameSpecs = new GameSpecs();
	private final Color pathColor = new Color(255, 255, 255);
	private final int pathThickness = 50;
	//private List <Position> linePositionList = new ArrayList <Position>();
	private List <Position> linePositionList = Arrays.asList(new Position(10,10),new Position(200,200));
	
	public SandboxModeController(Scene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((SandboxModeScene)this.scene).getView().getGameBoard().setLine(linePositionList, pathColor, pathThickness);
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
			Position lastSelectedPosition = listener.getLastNodeSelected();
			lastSelectedPosition.positionConverter(this.gameSpecs.getGameUnits(), ((SandboxModeScene)this.scene).getView().getPanel().getPreferredSize());
			this.linePositionList.add(lastSelectedPosition);
			this.listener.resetUpdate();
		}
	}
	
	public MouseListener getListener() {
		return this.listener;
	}
	

}
