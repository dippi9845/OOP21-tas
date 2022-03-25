package main.java.view;

import javax.swing.JPanel;

import main.java.controller.Controller;
import main.java.model.Entity;

public interface ViewComponent {
    
    abstract void resize();
    abstract void drawEntities();
    public void addEntityLabel(Entity entity);

    abstract JPanel getPanel();
    abstract void setObserver(Controller controller);

}
