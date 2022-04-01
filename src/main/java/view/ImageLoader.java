package main.java.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import main.java.model.Entity;

public interface ImageLoader {

    BufferedImage getImageByEntity(Entity entity, Dimension CanvasDimension);
    
}
