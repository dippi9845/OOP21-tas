package main.java.tas.view;

import java.awt.Dimension;
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
    BufferedImage getImageByEntity(Entity entity, Dimension CanvasDimension) throws FileNotFoundException;

}
