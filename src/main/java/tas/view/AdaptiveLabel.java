package main.java.tas.view;

import javax.swing.JLabel;

import main.java.tas.utils.GameSpecs;
import main.java.tas.utils.Position;

/**
 * Class that models a Label that adapt itself to the JComponent that contains
 * it.
 */
public class AdaptiveLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	private Position actualPosition = new Position(0, 0);

	private GameSpecs gameSpecs = new GameSpecs();

	/**
	 * Set the relative position of the label.
	 * 
	 * @param pos the relative position of the label
	 */
	public void setPosition(final Position pos) {
		this.actualPosition = pos;
		draw(this.actualPosition);
	}

	/**
	 * Redraws the component.
	 */
	public void redraw() {
		draw(this.actualPosition);
	}

	/**
	 * Method that draws the label.
	 * 
	 * @param pos is the relative position of the label
	 */
	private void draw(final Position pos) {
		int xPos = (int) ((double) getParent().getWidth() / (double) this.gameSpecs.getGameUnits().width
		        * (double) pos.getX() - getPreferredSize().getWidth() / 2);
		int yPos = (int) ((double) getParent().getHeight() / (double) this.gameSpecs.getGameUnits().height
		        * (double) pos.getY() - getPreferredSize().getHeight() / 2);

		setBounds(xPos, yPos, (int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight());
	}

}
