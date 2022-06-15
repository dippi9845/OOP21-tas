package main.java.tas.view;

import javax.swing.JPanel;

public interface ViewMouse {
	
	public JPanel getPanel();

	public void setMouseObserver(SceneMouseObserver observer);
}
