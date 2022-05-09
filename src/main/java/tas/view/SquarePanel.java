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

class SquarePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final HashMap<Entity, AdaptiveLabel> entityLables = new HashMap<Entity, AdaptiveLabel>();
    private final ImageLoader imGetter = new ImageLoaderImpl();

    @Override
    public Dimension getPreferredSize() {
        Container c = this.getParent();
        int size = Math.min(c.getHeight(), c.getWidth());
        Dimension d = new Dimension(size,size);
        return d;
    }
    
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

    public void setAdaptive() {
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

    public void redrawEntity(Entity e, Position newPos) {
        this.entityLables.get(e).setPosition(newPos);
    }

    public void removeEntity(Entity e) {
        this.remove(this.entityLables.get(e));
        this.revalidate();
        this.repaint();
        this.entityLables.remove(e);
    }
    
    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
    
}
