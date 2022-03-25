package main.java.view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;

import main.java.utils.Position;
import main.java.utils.GameSpecs;

public class AdaptiveLabel extends JLabel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Position actualPosition = new Position(0, 0);
    
    public void setPosition(Position pos) {
        this.actualPosition = pos;
        setBounds((int)this.actualPosition.getX(), (int)this.actualPosition.getY(), (int)getPreferredSize().getWidth(), (int)getPreferredSize().getHeight());
    }
    
    public void setAdaptive() throws NullPointerException {
        this.getParent().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int x = (int)((double)getParent().getWidth() / (double)GameSpecs.GAME_UNITS.width * (double)actualPosition.getX());
                int y = (int)((double)getParent().getHeight() / (double)GameSpecs.GAME_UNITS.height * (double)actualPosition.getY());
                Position newPos = new Position(x, y);
                
                if (getIcon() == null) {
                    setBounds((int)newPos.getX(), (int)newPos.getY(), (int)getPreferredSize().getWidth(), (int)getPreferredSize().getHeight());
                } else {
                    setBounds((int)newPos.getX(), (int)newPos.getY(), getIcon().getIconWidth(), getIcon().getIconHeight());
                }
            }
        });
    }

}
