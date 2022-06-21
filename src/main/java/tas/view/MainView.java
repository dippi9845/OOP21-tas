package main.java.tas.view;

import javax.swing.JPanel;

/**
 * Interface for a generic view.
 */
public interface MainView {

	/**
	 * Creates a window
	 */
	void createWindow();

	/**
	 * Set the window to visible
	 */
	void show();

	/**
	 * Updates the window
	 */
	void update();

	/**
	 * @return the main {@link JPanel} of the window
	 */
	JPanel getPanel();

	/**
	 * Disposes of the window.
	 */
	void dispose();

	/**
	 * Removes everything from the view
	 */
	void clearView();

	/**
	 * Closes the view
	 */
	void destroyView();

}
