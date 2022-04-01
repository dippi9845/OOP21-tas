package main.java.view;

import javax.swing.JPanel;

import main.java.controller.Controller;
import main.java.model.Entity;

public interface ViewComponent {
    
    void resize();
    void drawEntity(Entity entity);
    public void addEntityLabel(Entity entity);

    JPanel getPanel();
    void setObserver(Controller controller);

}
