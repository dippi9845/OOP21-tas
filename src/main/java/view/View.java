package main.java.view;

import javax.swing.JPanel;

public interface View {
    
    void createDefaultWindow();
    void show();
    
    JPanel getPanel();

}
