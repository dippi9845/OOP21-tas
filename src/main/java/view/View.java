package main.java.view;

import javax.swing.JPanel;

public interface View {
    
    void CreateDefaultWindow();
    void show();
    
    JPanel getPanel();

}
