package main.java.tas.view;

import javax.swing.JPanel;

public interface ViewAction {
	
	public JPanel getPanel();

	public void setActionObserver(SceneActionObserver observer);
}
