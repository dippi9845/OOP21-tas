package main.java.view;

import javax.swing.JPanel;

public interface View {
    
    void createWindow();
    void show();
    void update();
    
    JPanel getPanel();

}
