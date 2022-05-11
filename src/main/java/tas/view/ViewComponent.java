package main.java.tas.view;

import javax.swing.JPanel;

import main.java.tas.model.Entity;

/**
 * Interface that models a component of the view
 */
public interface ViewComponent {
    
    /**
     * Method that draws a given entity on the component
     * NOTE: the entity must be added first with {@link #addEntityLabel}
     * @param entity the entity that will be drawn
     */
    void drawEntity(Entity entity);
    
    /**
     * Create a label for the entity
     * @param entity that needs a label
     */
    void addEntityLabel(Entity entity);
    
    /**
     * Removes the label of a given entity
     * @param entity that will be removed
     */
    void removeEntityLabel(Entity entity);

    /**
     * @return the {@link JPanel} of the component
     */
    JPanel getPanel();
    
    /**
     * Set up the observer
     */
    void setObserver();

}
