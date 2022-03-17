package main.java.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewImpl implements View {

    private static final String WINDOW_NAME = "Towers and Stuff";
    private static final Dimension SCREEN_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    private final Dimension  defaultWindowSize = scaleDimension(SCREEN_SIZE, 2);
    private final Dimension  minWindowSize = scaleDimension(SCREEN_SIZE, 5);
    
    private JFrame frame;
    private JPanel rootPanel;
    
    @Override
    public void CreateDefaultWindow() {
        this.frame = new JFrame(WINDOW_NAME);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(defaultWindowSize);
        frame.setMinimumSize(minWindowSize);
        
        this.rootPanel = new JPanel();
        this.frame.getContentPane().add(this.rootPanel);
    }
    
    @Override
    public JPanel getPanel() {
        return this.rootPanel;
    }
    
    private Dimension scaleDimension(Dimension dimension, double proportion) {
        return new Dimension((int)(dimension.getWidth()/proportion), (int)(dimension.getHeight()/proportion));
    }
    
    @Override
    public void show() {
        this.frame.setVisible(true);
    }
    
}
