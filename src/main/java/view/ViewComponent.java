package main.java.view;

import java.util.List;

import javax.swing.JPanel;

import main.java.controller.Controller;
import main.java.model.Entity;

public interface ViewComponent {
    
    abstract void resize();
    abstract void drawEntities(List<Entity> entities);

    abstract JPanel getPanel();
    abstract void setObserver(Controller controller);

}
