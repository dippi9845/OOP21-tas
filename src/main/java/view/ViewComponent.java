package main.java.view;

import javax.swing.JPanel;

import main.java.controller.Controller;
import main.java.model.Entity;

public interface ViewComponent {
    
    void resize();
    void drawEntity(Entity entity);
    void addEntityLabel(Entity entity);
    void redrawEntities();

    JPanel getPanel();
    void setObserver(Controller controller);

}
