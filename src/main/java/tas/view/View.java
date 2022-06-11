package main.java.tas.view;

import javax.swing.JPanel;

/**
 * Interface for a generic view.
 */
public interface View {
   
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
     * disposes of the window
     */
    void dispose();
    
    /**
     * Closes the view
     */
    void destroyView();

}
