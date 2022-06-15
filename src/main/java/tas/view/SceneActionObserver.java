package main.java.tas.view;

import java.awt.event.ActionListener;

import main.java.tas.controller.SceneController;


public interface SceneActionObserver extends SceneController {

	public ActionListener getActionListener();
	
}
