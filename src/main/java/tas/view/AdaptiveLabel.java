package main.java.tas.view;

import javax.swing.JLabel;

import main.java.tas.utils.Position;
import main.java.tas.utils.GameSpecs;

/**
 * Class that models a Label that adapt itself to the JComponent that
 * contains it 
 */
public class AdaptiveLabel extends JLabel {

    private static final long serialVersionUID = 1L;
    private Position actualPosition = new Position(0, 0);
    
    /**
     * Set the relative position of the label
     * @param pos the relative position of the label
     */
    public void setPosition(Position pos) {
        this.actualPosition = pos;
        draw(this.actualPosition);
    }
    
    /**
     * Method that draws the label
     * @param pos is the relative position of the label
     */
    private void draw(Position pos) {
        int xPos = (int)((double)getParent().getWidth() / (double)GameSpecs.GAME_UNITS.width * (double)pos.getX() - getPreferredSize().getWidth() / 2);
        int yPos = (int)((double)getParent().getHeight() / (double)GameSpecs.GAME_UNITS.height * (double)pos.getY() - getPreferredSize().getHeight() / 2);

        setBounds(xPos, yPos, (int)getPreferredSize().getWidth(), (int)getPreferredSize().getHeight());
    }

}
