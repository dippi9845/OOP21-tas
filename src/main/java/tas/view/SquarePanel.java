package main.java.tas.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.java.tas.model.Entity;
import main.java.tas.utils.GameSpecs;
import main.java.tas.utils.Position;

/**
 * Class that implements a square version of the {@link JPanel}
 */
class SquarePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final HashMap<Entity, AdaptiveLabel> entityLables = new HashMap<Entity, AdaptiveLabel>();
    private final ImageLoader imGetter = new ImageLoaderImpl();
    private List<Position> linePoints = new ArrayList<Position>();;

    /**
     * Set up the SquarePanel
     */
    public SquarePanel() {
        super();
        setLines(Arrays.asList(new Position(500, 500), new Position(750, 750), new Position(0, 1000))); //TODO: fixxare
        setAdaptive();
    }
    
    public SquarePanel(List<Position> linesPoints) {
        this();
        setLines(linesPoints);
    }
    
    public void setLines(List<Position> linesPoints) throws IllegalArgumentException {
        if (linesPoints.size() == 1) {
            throw new IllegalArgumentException("@param linesPoints must contains at least 2 elements or 0 to remove the line!");
        }
        this.linePoints = linesPoints;
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.linePoints.isEmpty()) {
            return;
        }
        
        double scaleFactorX = this.getWidth() / GameSpecs.GAME_UNITS.getWidth();
        double scaleFactorY = this.getHeight() / GameSpecs.GAME_UNITS.getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke((int) (Math.max(scaleFactorX, scaleFactorY) * 100)));
        g2.setColor(new Color(255, 255, 255));
        for (int i=0; i < this.linePoints.size()-1; i++) {
            int x1 = (int) (this.linePoints.get(i).getX() * scaleFactorX);
            int y1 = (int) (this.linePoints.get(i).getY() * scaleFactorY);
            int x2 = (int) (this.linePoints.get(i+1).getX() * scaleFactorX);
            int y2 = (int) (this.linePoints.get(i+1).getY() * scaleFactorY);
            
            g2.drawLine(x1, y1, x2, y2);
        }
    };
    
    /** {@inheritDoc} */
    @Override
    public Dimension getPreferredSize() {
        Container c = this.getParent();
        int size = Math.min(c.getHeight(), c.getWidth());
        Dimension d = new Dimension(size,size);
        return d;
    }
    
    /**
     * Creates a label for the given entity and generate its image
     * @param e
     */
    public void addEntity(Entity e) {
        AdaptiveLabel entityLabel = new AdaptiveLabel();
        try {
            entityLabel.setIcon(new ImageIcon(imGetter.getImageByEntity(e, this.getPreferredSize())));
        } catch (FileNotFoundException e1) {
            System.out.println(e1);
        }
        entityLables.put(e, entityLabel);
        this.add(entityLabel);
    }

    /**
     * Method that set all the components of the panel to be resized with it
     */
    private void setAdaptive() {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                for (Map.Entry<Entity, AdaptiveLabel> entityMap: entityLables.entrySet()) {
                    try {
                        entityMap.getValue().setIcon(new ImageIcon(imGetter.getImageByEntity(entityMap.getKey(), getPreferredSize())));
                    } catch (FileNotFoundException e1) {
                        System.out.println(e1);
                    }
                }
            }
        });
    }

    /**
     * Allows to redraw a given entity
     * NOTE: The entity must be added first with {@link #addEntity} 
     * @param e the entity that has to be redrawn
     * @param newPos the position where the entity will be drawn
     */
    public void redrawEntity(Entity e, Position newPos) {
        this.entityLables.get(e).setPosition(newPos);
    }

    /**
     * Method that removes the label of a given entity
     * NOTE: The entity must be added first with {@link #addEntity} 
     * @param e the entity that has to be removed
     */
    public void removeEntity(Entity e) {
        this.remove(this.entityLables.get(e));
        this.revalidate();
        this.repaint();
        this.entityLables.remove(e);
    }
    
    // TODO: capire come gestire la cosa delle serializable
    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
    }

    // TODO: capire come gestire la cosa delle serializable
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
    
}
