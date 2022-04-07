package main.java.tas.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import main.java.tas.model.Entity;

public interface ImageLoader {

    BufferedImage getImageByEntity(Entity entity, Dimension CanvasDimension);
    
}
