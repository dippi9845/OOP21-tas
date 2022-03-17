package main.java.view;

import java.awt.Color;
import javax.swing.JPanel;

public class GameView implements ViewComponent {
    
    private final JPanel gameCanvas;
    
    public GameView() {
        this.gameCanvas = new JPanel();
        gameCanvas.setBackground(Color.black);    // testing
    }

    @Override
    public JPanel getPanel() {
        return this.gameCanvas;
    }

}
