package main.java.tas.view;

import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.java.tas.model.Entity;
import main.java.tas.utils.Position;

/**
 * Class that implements a square version of the {@link JPanel}
 */
class SquarePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final HashMap<Entity, AdaptiveLabel> entityLables = new HashMap<Entity, AdaptiveLabel>();
    private final ImageLoader imGetter = new ImageLoaderImpl();

    /**
     * Set up the SquarePanel
     */
    public SquarePanel() {
        super();
        setAdaptive();
    }
    
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
