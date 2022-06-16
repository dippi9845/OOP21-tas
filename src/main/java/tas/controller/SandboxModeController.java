package main.java.tas.controller;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import main.java.tas.model.menu.MenuModel;
import main.java.tas.utils.GameSpecs;
import main.java.tas.utils.LevelHandler;
import main.java.tas.utils.Position;
import main.java.tas.view.scene.SandboxModeScene;
import main.java.tas.view.SceneActionObserver;
import main.java.tas.view.SceneMouseObserver;
import main.java.tas.view.scene.GenericScene;

/**
 * Class that implements {@link SceneController}.
 */
public class SandboxModeController implements SceneActionObserver,SceneMouseObserver {
	
	private SandboxModeListener listener;
	private GenericScene scene;
	private MenuModel model;
	private boolean lineInitialized = false;
	private boolean firstNodeIsSelected = false;
	private final GameSpecs gameSpecs = new GameSpecs();
	private final Color pathColor = new Color(255, 255, 255);
	private final int pathThickness = 50;
	private List <Position> linePositionList = new ArrayList <Position>();
	private final ButtonListener doneButtonListener = new ButtonListener();
	/**
	 * Constructor that creates a menu controller for the sandbox mode menu.
	 * @param sceneIn the sandbox mode scene
	 * @param theModel the menu model
	 */
	public SandboxModeController(GenericScene sceneIn, MenuModel theModel) {
		scene = sceneIn;
		((SandboxModeScene) scene).setObserver(this);
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
			
			if (lineInitialized) {
				Position lastSelectedPosition = listener.getLastNodeSelected();
				lastSelectedPosition.positionConverter(this.gameSpecs.getGameUnits(), ((SandboxModeScene)this.scene).getView().getGameBoard().getPreferredSize());
				this.linePositionList.add(lastSelectedPosition);
				
			}
			else if(firstNodeIsSelected) {
				System.out.println("second node selected");
				Position lastSelectedPosition = listener.getLastNodeSelected();
				lastSelectedPosition.positionConverter(this.gameSpecs.getGameUnits(), ((SandboxModeScene)this.scene).getView().getGameBoard().getPreferredSize());
				this.linePositionList.add(lastSelectedPosition);
				((SandboxModeScene)this.scene).getView().getGameBoard().setLine(linePositionList, pathColor, pathThickness);
				this.lineInitialized = true;
			}
			else {
				System.out.println("fisrst node selected");
				Position lastSelectedPosition = listener.getLastNodeSelected();
				lastSelectedPosition.positionConverter(this.gameSpecs.getGameUnits(), ((SandboxModeScene)this.scene).getView().getGameBoard().getPreferredSize());
				this.linePositionList.add(lastSelectedPosition);
				this.firstNodeIsSelected = true;
			}
			this.listener.resetUpdate();
		}
		if (this.doneButtonListener.checkUpdate()) {
			LevelHandler.writeLevel(linePositionList);
			this.model.setMainScene(1);
			this.doneButtonListener.resetUpdate();
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public MouseListener getMouseListener() {
		return this.listener;
	}
	
	/**
	 * 
	 * @return the done button listener
	 */
	@Override
	public ActionListener getActionListener() {
		return this.doneButtonListener;
	}
	

}
