package main.java.tas.view;

import javax.swing.JLabel;

import main.java.tas.model.GameSpecs;
import main.java.tas.utils.Position;

/**
 * Class that models a Label that adapt itself to the JComponent that contains
 * it.
 */
public class AdaptiveLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	private Position actualPosition = new Position(0, 0);
	private CustomTextImpl text = new CustomTextImpl();

	private GameSpecs gameSpecs = new GameSpecs();

	/**
	 * Set the relative position of the label.
	 * 
	 * @param pos the relative position of the label
	 */
	public void setPosition(Position pos) {
		this.actualPosition = pos;
		draw(this.actualPosition);
	}

	/**
	 * Sets the font of the label.
	 * 
	 * @param fontName
	 * @param style
	 * @param size
	 */
	public void setFont(String fontName, int style, int size) {
		this.text.setFontName(fontName);
		this.text.setFontStyle(style);
		this.text.setFontSize(size);
		super.setFont(this.text.getFont());
	}

	/** {@inheritDoc} */
	@Override
	public void setText(String text) {
		if (this.text == null) {
			this.text = new CustomTextImpl();
		}

		this.text.setText(text);
		super.setText(this.text.getText());
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
	private void draw(Position pos) {
		int xPos = (int) ((double) getParent().getWidth() / (double) this.gameSpecs.getGameUnits().width
				* (double) pos.getX() - getPreferredSize().getWidth() / 2);
		int yPos = (int) ((double) getParent().getHeight() / (double) this.gameSpecs.getGameUnits().height
				* (double) pos.getY() - getPreferredSize().getHeight() / 2);

		setBounds(xPos, yPos, (int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight());
	}

}
