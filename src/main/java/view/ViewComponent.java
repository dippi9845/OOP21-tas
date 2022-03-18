package main.java.view;

import javax.swing.JPanel;

import main.java.controller.Controller;

public interface ViewComponent {
    
    JPanel getPanel();

    void setObserver(Controller controller);

}
