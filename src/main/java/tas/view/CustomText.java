package main.java.tas.view;

import java.awt.Font;

public interface CustomText {

    /**
     * Set up the font name
     * @param fontName
     */
    void setFontName(String fontName);

    /**
     * Set up the font style
     * @param style
     */
    void setFontStyle(int style);

    /**
     * Set up the size of the font
     * @param size
     */
    void setFontSize(int size);
    
    /**
     * @return the built Font
     */
    Font getFont();
    
    /**
     * Sets the text
     * @param text
     */
    void setText(String text);
    
    /**
     * @return the built text
     */
    String getText();
    
}
