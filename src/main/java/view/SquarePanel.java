package main.java.view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;

class SquarePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Dimension getPreferredSize() {
        Container c = this.getParent();
        int size = Math.min(c.getHeight(), c.getWidth());
        Dimension d = new Dimension(size,size);
        return d;
    }
    
}
