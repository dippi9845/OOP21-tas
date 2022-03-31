package main.java.view;

import javax.swing.JPanel;

public interface View {
    
    void createWindow();
    void show();
    
    JPanel getPanel();

}
