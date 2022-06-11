package main.java.tas.view;

import java.awt.Font;

/**
 * Interface for class that handles custom text.
 */
public interface CustomText {

	/**
	 * Set up the font name.
	 * 
	 * @param fontName
	 */
	void setFontName(final String fontName);

	/**
	 * Set up the font style.
	 * 
	 * @param style
	 */
	void setFontStyle(final int style);

	/**
	 * Set up the size of the font.
	 * 
	 * @param size
	 */
	void setFontSize(final int size);

	/**
	 * @return the built Font
	 */
	Font getFont();

	/**
	 * Sets the text.
	 * 
	 * @param text
	 */
	void setText(final String text);

	/**
	 * @return the built text
	 */
	String getText();

}
