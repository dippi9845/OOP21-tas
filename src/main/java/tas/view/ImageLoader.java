package main.java.tas.view;

import main.java.tas.utils.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import main.java.tas.model.Entity;

/**
 * Interface for an image loader.
 */
public interface ImageLoader {

	/**
	 * Return the image of a given entity. NOTE: There must be an image with the
	 * entity name (with the first letter lower case) in the given directory.
	 * 
	 * @param entity          that needs an image
	 * @param CanvasDimension the dimension of the canvas
	 * @return the requested image
	 * @throws FileNotFoundException if there is no image
	 */
	BufferedImage getImageByEntity(final Entity entity, Dimension CanvasDimension) throws FileNotFoundException;

	/**
	 * Return the image by the given name and re-scaled to the requested dimension
	 * 
	 * @param imageName      the name of the image
	 * @param imageDimension the dimension of the image
	 * @return the requested image
	 * @throws FileNotFoundException if there is no image
	 */
	BufferedImage getImageByName(final String imageName, Dimension imageDimension) throws FileNotFoundException;

	/**
	 * Returns the requested image
	 * 
	 * @param imageName the name of the image
	 * @return the requested image
	 * @throws FileNotFoundException if there is no image
	 */
	BufferedImage getImageByName(final String imageName) throws FileNotFoundException;

}
