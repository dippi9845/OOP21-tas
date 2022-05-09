package main.java.tas.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import main.java.tas.model.Entity;

public interface ImageLoader {

    BufferedImage getImageByEntity(Entity entity, Dimension CanvasDimension) throws FileNotFoundException;
    
}
