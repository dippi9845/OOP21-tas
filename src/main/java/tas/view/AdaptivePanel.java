package main.java.tas.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import main.java.tas.utils.GameSpecs;
import main.java.tas.utils.Position;

/**
 * Abstract class that models an adaptive version of {@link JPanel}
 */
public abstract class AdaptivePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private List<Position> linePoints = new ArrayList<Position>();
    private Color lineColor;
    private int lineThickness;
    
    /**
     * Set up a line that will be drawn at every frame
     * @param linesPoints the points of the line
     * NOTE: The input number must be greater than 1 or 0 to remove the line
     * @param color the color of the line
     * @param thickness the thickness of the line
     * @throws IllegalArgumentException if the given points number is 1
     */
    public void setLine(List<Position> linesPoints, Color color, int thickness) throws IllegalArgumentException {
        if (linesPoints.size() == 1) {
            throw new IllegalArgumentException("@param linesPoints must contains at least 2 elements or 0 to remove the line!");
        }
        this.lineColor = color;
        this.lineThickness = thickness;
        this.linePoints = linesPoints;
    }
    
    /** {@inheritDoc} */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.linePoints.isEmpty()) {
            return;
        }
        
        double scaleFactorX = this.getWidth() / GameSpecs.GAME_UNITS.getWidth();
        double scaleFactorY = this.getHeight() / GameSpecs.GAME_UNITS.getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke((int) (Math.max(scaleFactorX, scaleFactorY) * this.lineThickness)));
        g2.setColor(this.lineColor);
        for (int i=0; i < this.linePoints.size()-1; i++) {
            int x1 = (int) (this.linePoints.get(i).getX() * scaleFactorX);
            int y1 = (int) (this.linePoints.get(i).getY() * scaleFactorY);
            int x2 = (int) (this.linePoints.get(i+1).getX() * scaleFactorX);
            int y2 = (int) (this.linePoints.get(i+1).getY() * scaleFactorY);
            
            g2.drawLine(x1, y1, x2, y2);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public Dimension getPreferredSize() {
        Container c = this.getParent();
        int size = Math.min(c.getHeight(), c.getWidth());
        Dimension d = new Dimension(size,size);
        return d;
    }
    
}
