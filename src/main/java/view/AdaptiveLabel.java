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
        draw(this.actualPosition);
    }
    
    private void draw(Position pos) {
        int xPos = (int)((double)getParent().getWidth() / (double)GameSpecs.GAME_UNITS.width * (double)pos.getX() - getPreferredSize().getWidth() / 2);
        int yPos = (int)((double)getParent().getHeight() / (double)GameSpecs.GAME_UNITS.height * (double)pos.getY() - getPreferredSize().getHeight() / 2);

        setBounds(xPos, yPos, (int)getPreferredSize().getWidth(), (int)getPreferredSize().getHeight());
    }
    
    public void setAdaptive() throws NullPointerException {
        this.getParent().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                draw(actualPosition);
            }
        });
    }

}
