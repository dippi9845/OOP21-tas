package main.java.tas.controller;

import java.awt.event.ActionListener;


public interface SceneActionObserver extends SceneController {

	public ActionListener getActionListener();
	
}
