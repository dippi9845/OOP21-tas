package main.java.view;

import java.util.List;

import javax.swing.JPanel;

import main.java.controller.Controller;
import main.java.model.Entity;

public interface ViewComponent {
    
    JPanel getPanel();
    void drawEntities(List<Entity> entities);

    void setObserver(Controller controller);

}
