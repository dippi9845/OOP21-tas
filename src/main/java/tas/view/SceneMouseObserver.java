package main.java.tas.view;

import java.awt.event.MouseListener;

import main.java.tas.controller.SceneController;

public interface SceneMouseObserver extends SceneController{
	
	
	public MouseListener getMouseListener ();
}
