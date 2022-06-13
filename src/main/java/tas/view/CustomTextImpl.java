package main.java.tas.view;

import java.awt.Font;

/**
 * Class that implements {@link CustomText}.
 */
public class CustomTextImpl implements CustomText {

	private String fontName = "Arial";
	private int fontStyle = 1;
	private int fontSize = 11;

	private final String initString = "<html>";
	private final String endString = "</html>";
	private String text = "";

	/** {@inheritDoc} */
	@Override
	public void setFontName(final String fontName) {
		this.fontName = fontName;
	}

	/** {@inheritDoc} */
	@Override
	public void setFontStyle(final int style) {
		this.fontStyle = style;
	}

	/** {@inheritDoc} */
	@Override
	public void setFontSize(final int size) {
		this.fontSize = size;
	}

	/** {@inheritDoc} */
	@Override
	public Font getFont() {
		return new Font(this.fontName, this.fontStyle, this.fontSize);
	}

	/** {@inheritDoc} */
	@Override
	public void setText(final String text) {
		this.text = text;
	}

	/** {@inheritDoc} */
	@Override
	public String getText() {
		return this.initString + this.text + this.endString;
	}

}
